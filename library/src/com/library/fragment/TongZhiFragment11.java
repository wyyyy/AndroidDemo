package com.library.fragment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.Header;

import com.library.activity.R;
import com.library.adapter.AdapterTongZhi;
import com.library.bean.EntityZh.ZhihuDxysRoot;
import com.library.dao.ServiceZhihuDXYS;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import cn.common.application.UrlUtils;
import cn.utis.asyncUtils.AsyncRequestUtil;

public class TongZhiFragment11 extends Fragment
{

  private ArrayList<ZhihuDxysRoot> listCatalogsData = new ArrayList<ZhihuDxysRoot>();
  private AdapterTongZhi mAdapter;
  private View view;
  private SwipeRefreshLayout swipeRefreshLayout;
  int offsetNO = 0;

  // private String urlGET = UrlUtils.urlZHihuBase + offsetNO;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {

    view = inflater.inflate(R.layout.activity_tong_zhi, container, false);
    ListView listView = (ListView) view.findViewById(R.id.atv_tongzhi_list_view);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      {
        if (position >= 0)
        {
          if (mAdapter != null)
          {
            try
            {
              TextView textView = (TextView) view.findViewById(R.id.txt_listitem_tongzhi);
              ZhihuDxysRoot rttag = new ZhihuDxysRoot();
              rttag = (ZhihuDxysRoot) textView.getTag();
              Intent intent = new Intent();
              intent.setClass(getActivity().getApplicationContext(), ParaSetActivity.class);
              Bundle bundle = new Bundle();
              bundle.putSerializable("TAG", rttag);
              intent.putExtras(bundle);
              intent.putExtra("name", "name");
              startActivity(intent);
            } catch (Exception ex)
            {
            }
          }

        }
      }
    });
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.atv_tongzhi_swip);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
    {

      @Override
      public void onRefresh()
      {
        swipeRefreshLayout.setRefreshing(true);
        String urlGET = UrlUtils.urlZHihuBase + offsetNO;
        AsyncRequestUtil.getAsyInstance().get(urlGET, new AsyncHttpResponseHandler()
        {

          @Override
          public void onFailure(int stateCode, Header[] header, byte[] rtByte, Throwable arg3)
          {
            int i = 0;

            while (i < header.length)
            {

              System.out.println(header[i].getName() + ":  " + header[i].getValue());

              i++;

            }
          }

          @Override
          public void onSuccess(int stateCode, Header[] header, byte[] rtByte)
          {
            offsetNO += 10;
            if (stateCode == 200)
            {
              String str;
              try
              {
                str = new String(rtByte, "GB2312");
                listCatalogsData.addAll(0, ServiceZhihuDXYS.ReadJsonArr(str));
                mAdapter.notifyDataSetChanged();
              } catch (UnsupportedEncodingException e)
              {
                e.printStackTrace();
              }

              swipeRefreshLayout.setRefreshing(false);
            }

          }
        });

      }
    });

    swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    mAdapter = new AdapterTongZhi(getActivity().getApplicationContext(), listCatalogsData);
    listView.setAdapter(mAdapter);

    return view;
  }

}