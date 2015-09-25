package com.library.fragment;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.library.activity.R;
import com.library.data.MainAdapterList;

public class TongZhiFragment extends Fragment
{

	private MainAdapterList mAdapter;
	private PtrClassicFrameLayout mPtrFrame;
	// 泛型集合ArrayList
	private ArrayList<String> arrayList = new ArrayList<String>();

	private String[] itemsStrings =
	{ "拍照上传", "传照片", "写日志", "发状态", "新鲜事", "个人主页", "好友", "地点", "消息", "站内信" };
	LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		prpArrlistData();
		View contentView = inflater.inflate(R.layout.activity_tong_zhi,
				container, false);
		this.inflater = inflater;
		final ListView listView = (ListView) contentView
				.findViewById(R.id.rotate_header_list_view);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (position >= 0)
				{
					if (mAdapter != null)
					{
						/*
						 * final String url = mAdapter.getItem(position)
						 * .optString("pic"); if (!TextUtils.isEmpty(url)) { //
						 * WZh
						 * 
						 * getContext().pushFragmentToBackStack(
						 * MaterialStyleFragment.class, url);
						 * 
						 * }
						 */
					}

				}
			}
		});
		mAdapter = new MainAdapterList(inflater, arrayList);
		listView.setAdapter(mAdapter);

		mPtrFrame = (PtrClassicFrameLayout) contentView
				.findViewById(R.id.rotate_header_list_view_frame);
		mPtrFrame.setLastUpdateTimeRelateObject(this);
		mPtrFrame.setPtrHandler(new PtrHandler()
		{
			@Override
			public void onRefreshBegin(PtrFrameLayout frame)
			{
				updateData();
			}

			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame,
					View content, View header)
			{
				return PtrDefaultHandler.checkContentCanBePulledDown(frame,
						content, header);
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
				mPtrFrame.autoRefresh();
			}
		}, 100);
		return contentView;
	}

	private void prpArrlistData()
	{

		for (int i = 0; i < itemsStrings.length; i++)
		{
			arrayList.add((i + 1) + "." + itemsStrings[i]);
		}
	}

	protected void updateData()
	{

		final String[] mStrings =
		{ "1111111", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee", "Ffffff",
				"Gggggg", "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll",
				"Mmmmmm", "Nnnnnn", };

		try
		{
			mPtrFrame.postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					int index = arrayList.size();
					for (int i = 0; i < mStrings.length; i++)
					{
						index++;
						arrayList.add(index + ":" + mStrings[i]);
					}
					mPtrFrame.refreshComplete();
					mAdapter.notifyDataSetChanged();
				}
			}, 0);
		} catch (Exception ex)
		{
		}

	}

	/*
	 * private class ViewHolder extends ViewHolderBase<JsonData> {
	 * 
	 * private CubeImageView mImageView;
	 * 
	 * @Override public View createView(LayoutInflater inflater) { View v =
	 * inflater.inflate(R.layout.list_view_item, null); mImageView =
	 * (CubeImageView) v .findViewById(R.id.list_view_item_image_view);
	 * mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP); return v; }
	 * 
	 * @Override public void showData(int position, JsonData itemData) {
	 * mImageView.loadImage(mImageLoader, itemData.optString("pic")); } }
	 */
	// load string data

}