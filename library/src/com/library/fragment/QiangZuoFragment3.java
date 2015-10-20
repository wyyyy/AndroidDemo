package com.library.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.library.activity.R;
import com.library.adapter.AdapterImgDongtai;
import com.library.bean.EntityZh.RootZhihu;
import com.library.view.AddPopWindow;

/*
 * 底部3.抢座
 */
public class QiangZuoFragment3 extends Fragment
{
	private SwipeRefreshLayout swipeRefreshLayout;
	private View view;
	private ImageView iv_add;
	private ListView mListView;
	List<RootZhihu> listCatalogsData = new ArrayList<RootZhihu>();
	AdapterImgDongtai adapter;
	Context content;
	int offsetNO = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.fragment_qiangzuo, container, false);
		iv_add = (ImageView) view.findViewById(R.id.iv_add_qz);
		mListView = (ListView) view.findViewById(R.id.listView_qz);
		content = getActivity();
		// 点击右边显示
		iv_add.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				AddPopWindow addPopWindow = new AddPopWindow(getActivity());
				addPopWindow.showPopupWindow(iv_add);
			}

		});
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_qz);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{

			@Override
			public void onRefresh()
			{
				offsetNO = offsetNO + 10;
			}
		});

		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		// adapter = new AdapterImgDongtai(content, rtValue);

		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long arg3)
			{
				@SuppressWarnings("unused")
				String str = "map==null";
				try
				{
					TextView textView = (TextView) view.findViewById(R.id.row_text);
					@SuppressWarnings("unused")
					ImageView imageView = (ImageView) view.findViewById(R.id.row_image);
					RootZhihu rttag = new RootZhihu();
					rttag = (RootZhihu) textView.getTag();
					str = rttag.getPublishedTime();
					Intent intent = new Intent();
					intent.setClass(content, ParaSetActivity.class);
					Bundle bundle = new Bundle();
					// 传递name参数为tinyphp
					bundle.putSerializable("TAG", rttag);
					// bundle.putSerializable("TAGIMG", eImg);
					intent.putExtras(bundle);

					intent.putExtra("name", "name");
					startActivity(intent);
				} catch (Exception ex)
				{
					str = ex.toString();
				}

			}
		});

		return view;
	}

}
