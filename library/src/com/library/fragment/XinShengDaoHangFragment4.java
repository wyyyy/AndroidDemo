package com.library.fragment;

import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

import com.library.activity.R;
import com.library.adapter.AdapterXinSheng;
import com.library.bean.shfzhihu.ShfRoot;
import com.library.dao.ServiceZhiHuSHF;
import com.library.view.AddPopWindow;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import cn.common.application.UrlUtils;
import cn.utis.asyncUtils.AsyncRequestUtil;

/*
 * 底部菜单2.新生导航
 */
public class XinShengDaoHangFragment4 extends Fragment
{
  private ArrayList<ShfRoot> listCatalogsData = new ArrayList<ShfRoot>();
  int offsetNO = 1;
  private ImageView iv_add;
  View view;
  private SwipeRefreshLayout swipeRefreshLayout;
  private AdapterXinSheng mAdapter;
  private LayoutInflater myInflater;
  ArrayList<ShfRoot> tempList;
  Context myContext;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    view = inflater.inflate(R.layout.activity_xin_sheng_dao_hang_fragment, container, false);
    myInflater = inflater;
    myContext = getActivity();
    iv_add = (ImageView) view.findViewById(R.id.img_atv_xsdh);
    ListView listView = (ListView) view.findViewById(R.id.atv_xinsheng_list_view);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.atv_xinsheng_swip);
    iv_add.setOnClickListener(new OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        AddPopWindow addPopWindow = new AddPopWindow(getActivity());
        addPopWindow.showPopupWindow(iv_add);
      }

    });

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
    {

      @Override
      public void onRefresh()
      {
        getMoreData();

      }
    });

    swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    mAdapter = new AdapterXinSheng(getActivity().getApplicationContext(), myInflater,
        listCatalogsData);
    listView.setAdapter(mAdapter);
    return view;
  }

  void getMoreData()
  {
    swipeRefreshLayout.setRefreshing(true);
    RequestParams params = new RequestParams();
    params.put("page", offsetNO);
    Header headUser_Agent = new Header()
    {

      @Override
      public String getValue()
      {
        return "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2";
      }

      @Override
      public String getName()
      {
        return "User-Agent";
      }

      @Override
      public HeaderElement[] getElements() throws ParseException
      {
        return null;
      }
    };
    Header[] header = new Header[]
    { headUser_Agent };
    // because 403 so add header
    AsyncRequestUtil.getAsyInstance().get(myContext, UrlUtils.urlZhihuSHF, header, params,
        new TextHttpResponseHandler()
        {

          @Override
          public void onFailure(int statusCode, Header[] header, String response, Throwable error)
          {

            Toast.makeText(getActivity(), "onFailure" + statusCode + error.toString(),
                Toast.LENGTH_LONG).show();
            System.err.println(error.toString());
            swipeRefreshLayout.setRefreshing(false);

          }

          @Override
          public void onSuccess(int arg0, Header[] header, String response)
          {
            offsetNO += 1;
            if (true)
            {
              try
              {
                tempList = new ArrayList<ShfRoot>();
                tempList = ServiceZhiHuSHF.getCountShfByStr(response);
                if (tempList == null)
                {
                  Toast.makeText(getActivity(), "tempList == null", Toast.LENGTH_LONG).show();
                } else
                {
                  if (listCatalogsData == null)
                  {
                    Toast.makeText(getActivity(), "listCatalogsData==null", Toast.LENGTH_LONG)
                        .show();
                  } else
                  {
                    listCatalogsData.addAll(0, tempList);
                    mAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                  }
                }
              } catch (Exception e)
              {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
              } finally
              {
                swipeRefreshLayout.setRefreshing(false);
              }

            }

          }
        });

  }
}
