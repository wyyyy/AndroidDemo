package com.library.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.library.activity.R;

/*
 * for main activity
 */
@SuppressLint(
{ "ViewHolder", "InflateParams" })
public class MainAdapter extends BaseAdapter
{
	@SuppressWarnings("unused")
	private Context mycContext;
	private String[] itemsStrings;// 标题方案
	LayoutInflater factory;

	public MainAdapter()
	{
	}

	public MainAdapter(LayoutInflater layoutInflater, String[] item)
	{
		// TODO Auto-generated constructor stub
		factory = layoutInflater;
		itemsStrings = item;
		// icons = iicons;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return itemsStrings.length;
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return itemsStrings[position];
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View View, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		// LayoutInflater factory = LayoutInflater.from(mycContext);
		View iniView = factory.inflate(R.layout.list_view_item_tongzhi, null);
		TextView textView = (TextView) iniView.findViewById(R.id.txt_listitem_tongzhi);
		textView.setText(itemsStrings[position]);// pictureName
		return iniView;
	}

}
