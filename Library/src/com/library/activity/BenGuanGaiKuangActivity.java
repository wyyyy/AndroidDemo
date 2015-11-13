package com.library.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.library.adapter.AdapterGridForBggk;
import com.library.bean.bizhi.BizhiRoot;
import com.library.bean.bizhi.WallPapers;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import cn.utis.asyncUtils.AsyncRequestUtil;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class BenGuanGaiKuangActivity extends Activity
{
  private PtrClassicFrameLayout mPtrFrame;
  private GridView gridView;
  private AdapterGridForBggk myAdapter;
  BizhiRoot rtValue = new BizhiRoot();
  List<WallPapers> rtList = new ArrayList<WallPapers>();
  String strUrlAdd = "";
  Boolean ifFirst = true;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ben_guan_gai_kuang);
    gridView = (GridView) this.findViewById(R.id.grid_bggk);
    myAdapter = new AdapterGridForBggk(getApplicationContext(), rtList);
    gridView.setAdapter(myAdapter);
    mPtrFrame = (PtrClassicFrameLayout) this.findViewById(R.id.frame_bggk);
    mPtrFrame.setLastUpdateTimeRelateObject(this);
    mPtrFrame.setPtrHandler(new PtrHandler()
    {

      @Override
      public void onRefreshBegin(PtrFrameLayout frame)
      {
        updateData();
      }

      @Override
      public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header)
      {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
      }
    });
    // the following are default settings
    mPtrFrame.setResistance(1.7f);
    mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
    mPtrFrame.setDurationToClose(200);
    mPtrFrame.setDurationToCloseHeader(1000);
    // default is false
    mPtrFrame.setPullToRefresh(false);
    // default is true
    mPtrFrame.setKeepHeaderWhenRefresh(true);
    mPtrFrame.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        // mPtrFrame.autoRefresh();
      }
    }, 100);
    setupViews(mPtrFrame);

  }

  protected void setupViews(final PtrClassicFrameLayout ptrFrame)
  {

  }

  protected void updateData()
  {

    mPtrFrame.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        getData();

      }
    }, 0);
  }

  public void onClick_fanhui(View v)
  {
    this.finish();
    this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);

  }

  void getData()
  {
    RequestParams params = new RequestParams();
    params.put("format", "2");
    params.put("key", "ee37c488ea30af4dbb1d4a5a997d821c");
    String strurl = "http://bizhi.sogou.com/cate/getCate/4";
    if (ifFirst != true)
    {
      if (rtValue != null)
      {
        strUrlAdd = strurl + "/"
            + rtValue.getWallpapers().get(rtValue.getWallpapers().size() - 1).getWp_id();
        strurl = strUrlAdd;
      }
    } else
    {
      strurl = "http://bizhi.sogou.com/cate/getCate/4/1230702";
    }
    AsyncRequestUtil.getAsyInstance().get(strurl, new TextHttpResponseHandler()
    {

      @Override
      public void onSuccess(int statusCode, Header[] header, String strResponse)
      {
        ifFirst = false;
        if (strResponse.equals(""))
        {
        } else
        {
          rtValue = new Gson().fromJson(strResponse, BizhiRoot.class);
          String str = "";
          String strUrl = "http://img01.sogoucdn.com/app/a/100540002/";
          // http://img01.sogoucdn.com/app/a/100540002//1224128_s_90_2_219x160.jpg
          WallPapers wTemp = new WallPapers();
          for (int i = 0; i < rtValue.getWallpapers().size(); i++)
          {
            wTemp = new WallPapers();
            str = rtValue.getWallpapers().get(i).getWp_fileadd_s();
            rtValue.getWallpapers().get(i).setWp_fileadd_s(strUrl + str);
            wTemp.setWp_fileadd_s(strUrl + str);
            rtList.add(0, wTemp);
          }
          myAdapter.notifyDataSetChanged();
          mPtrFrame.refreshComplete();
        }

      }

      @Override
      public void onFailure(int statusCode, Header[] header, String strResponse, Throwable arg3)
      {
        Toast.makeText(getApplicationContext(), statusCode + "", Toast.LENGTH_LONG).show();
        mPtrFrame.refreshComplete();
      }
    });
  }
}
