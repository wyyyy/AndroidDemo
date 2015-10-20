package com.library.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.library.activity.R;
import com.library.bean.EntityZh.RootZhihu;

/*
 * 顶部-借阅历史
 */
public class JieYueLiShiFragment22 extends Fragment
{
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView mListView;
	List<String> urList = new ArrayList<String>();
	int offsetNO = 0;
	String rtStr = "";
	String strBaseUrl = "http://zhuanlan.zhihu.com/api/columns/dingxiangyisheng/posts?limit=10&offset=";
	List<RootZhihu> listCatalogsData = new ArrayList<RootZhihu>();
	List<Uri> imageUri = new ArrayList<Uri>();
	Context context;
	File cache;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.activity_jie_yue_li_shi_fragment, container, false);
		// 创建缓存目录，系统一运行就得创建缓存目录的，
		cache = new File(Environment.getExternalStorageDirectory(), "cache");

		if (!cache.exists())
		{
			cache.mkdirs();
		}
		context = getActivity();
		urList.add(strBaseUrl + offsetNO);
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
		mListView = (ListView) view.findViewById(R.id.Swipelistview);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{

			@Override
			public void onRefresh()
			{
			}
		});

		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		mListView.setAdapter(null);
		return view;
	}

}
