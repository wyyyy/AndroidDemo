package com.library.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.library.activity.R;
import com.library.view.AddPopWindow;

/*
 * 底部菜单栏-1.互动
 */
public class HuDongFragment1 extends Fragment implements OnCheckedChangeListener
{

	private View parentView;
	private RadioGroup radioGroup;
	private RadioButton rbTongZhi, rbDongTai;
	private ViewPager vp;

	List<Fragment> list = null;
	private ImageView iv_add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_news, container, false);

		radioGroup = (RadioGroup) parentView.findViewById(R.id.radioGroup);
		rbTongZhi = (RadioButton) parentView.findViewById(R.id.rbTongZhi);
		rbDongTai = (RadioButton) parentView.findViewById(R.id.rbDongTai);
		vp = (ViewPager) parentView.findViewById(R.id.viewpagerHuDong);
		iv_add = (ImageView) parentView.findViewById(R.id.iv_add);

		list = new ArrayList<Fragment>();
		TongZhiFragment11 tzf = new TongZhiFragment11();
		DongTaiFragment12 dtf = new DongTaiFragment12();
		list.add(tzf);
		list.add(dtf);

		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
		vp.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();

		radioGroup.setOnCheckedChangeListener(this);
		rbTongZhi.setChecked(true);

		// 滑动切换
		vp.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int arg0)
			{
				switch (arg0)
				{
				case 0:
					rbTongZhi.setChecked(true);
					break;
				case 1:
					rbDongTai.setChecked(true);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{

			}
		});

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

		return parentView;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int cheakedId)
	{
		if (cheakedId == rbTongZhi.getId())
		{
			vp.setCurrentItem(0);
		} else if (cheakedId == rbDongTai.getId())
		{
			vp.setCurrentItem(1);
		}
	}

	class ZxzcAdapter extends FragmentStatePagerAdapter
	{
		public ZxzcAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> list2)
		{
			super(fm);
			this.list = list2;
			// TODO Auto-generated constructor stub
		}

		List<Fragment> list;

		@Override
		public android.support.v4.app.Fragment getItem(int arg0)
		{
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return list.size();
		}

	}
}
