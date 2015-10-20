package com.library.data;

import java.util.ArrayList;

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
public class MainAdapterList extends BaseAdapter
{
	@SuppressWarnings("unused")
	private Context mycContext;
	@SuppressWarnings("unused")
	private String[] itemsStrings;// 标题方案
	LayoutInflater factory;
	ArrayList<String> arrayList=new ArrayList<String>();  

	public MainAdapterList(LayoutInflater inflater, ArrayList<String> _arrayList)
	{
		// TODO Auto-generated constructor stub
		factory = inflater;
		arrayList = _arrayList;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		if (arrayList == null)
		{
			return 0;
		} else
		{
			return arrayList.size();

		}
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) @Override
	public View getView(int position, View View, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		// LayoutInflater factory = LayoutInflater.from(mycContext);
		View iniView = factory
				.inflate(R.layout.list_view_item_tongzhi, null);
		TextView textView = (TextView) iniView.findViewById(R.id.txt_listitem_tongzhi);
		textView.setText(arrayList.get(position));// pictureName
		return iniView;
	}

}
