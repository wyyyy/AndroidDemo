package com.library.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.library.activity.R;
import com.library.adapter.AdapterWZJK;
import com.library.application.UrlUtils;
import com.library.bean.xiao.XHData;
import com.library.dao.ServiceXiaohua;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class WeiZhangJiaoKuanFragment23 extends Fragment
{
	private ArrayList<XHData> listCatalogsData = new ArrayList<XHData>();
	private SwipeRefreshLayout swipeRefreshLayout;
	private AdapterWZJK mAdapter;
	List<String> urList = new ArrayList<String>();
	int offsetNO = 0;
	String rtStr = "";
	String strBaseUrl = UrlUtils.urlXisohuaBase;
	private LayoutInflater myInflater;
	Context myContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.activity_wei_zhang_jiao_kuan_fragment, container, false);
		myInflater = inflater;
		myContext = getActivity();
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_wzjk);
		ListView mListView = (ListView) view.findViewById(R.id.swipelistview_wzjk);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{

			@Override
			public void onRefresh()
			{
				swipeRefreshLayout.setRefreshing(true);
				String urlGET = UrlUtils.urlZHihuBase + System.currentTimeMillis();
				RequestParams params = new RequestParams();
				params.put("page", offsetNO);
				// final ArrayList<RootZhihu> tempList = new
				// ArrayList<RootZhihu>();
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(myContext, urlGET, params, new TextHttpResponseHandler()
				{
					public void onFailure(int arg0, Header[] arg1, String response, Throwable arg3)
					{
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, String response)
					{
						if (true)
						{
							listCatalogsData.addAll(0, ServiceXiaohua.ReadJsonArr(response));
							mAdapter.notifyDataSetChanged();
							swipeRefreshLayout.setRefreshing(false);
						}

					}
				});

			}

		});

		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		//
		mAdapter = new AdapterWZJK(getActivity().getApplicationContext(), myInflater, listCatalogsData);
		mListView.setAdapter(mAdapter);

		return view;
	}

}
