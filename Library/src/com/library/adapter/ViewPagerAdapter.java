package com.library.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter
{

	private List<View> views;
	@SuppressWarnings("unused")
	private Context context;

	public ViewPagerAdapter(Context context, List<View> views)
	{
		this.context = context;
		this.views = views;
	}

	// 当view不需要时将其销毁
	@Override
	public void destroyItem(View container, int position, Object object)
	{

		// removeView方法移除view
		((ViewPager) container).removeView(views.get(position));
	}

	// 调用instantiateItem方法，类似BaseAdapter里面的getView方法
	@Override
	public Object instantiateItem(View container, int position)
	{
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	// 返回view当前的数量
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return views.size();
	}

	// 判断当前的view是不是需要的一个对象
	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

}
