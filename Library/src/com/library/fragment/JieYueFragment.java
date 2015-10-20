package com.library.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
 * 底部菜单2.借阅
 */
public class JieYueFragment extends Fragment implements OnCheckedChangeListener
{

	private RadioGroup radioGroup;
	private RadioButton rb_DangQian, rb_JieYue, rb_WeiZhang;
	private ViewPager vp;
	List<Fragment> list = null;

	private ImageView iv_add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_contacts, container, false);
		vp = (ViewPager) view.findViewById(R.id.vp_JieYue);
		radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_jieyue);
		rb_DangQian = (RadioButton) view.findViewById(R.id.rb_dangqianjieyue);
		rb_JieYue = (RadioButton) view.findViewById(R.id.rb_jieyuelishi);
		rb_WeiZhang = (RadioButton) view.findViewById(R.id.rb_weizhangjiaokuan);
		iv_add = (ImageView) view.findViewById(R.id.iv_add);

		list = new ArrayList<Fragment>();
		DangQianJieYueFragment21 dqjf = new DangQianJieYueFragment21();
		JieYueLiShiFragment22 jylf = new JieYueLiShiFragment22();
		WeiZhangJiaoKuanFragment23 wzjf = new WeiZhangJiaoKuanFragment23();
		list.add(dqjf);
		list.add(jylf);
		list.add(wzjf);
		// WZH
		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
		vp.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();

		radioGroup.setOnCheckedChangeListener(this);
		rb_DangQian.setChecked(true);

		// 滑动切换
		vp.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int arg0)
			{
				switch (arg0)
				{
				case 0:
					rb_DangQian.setChecked(true);
					break;
				case 1:
					rb_JieYue.setChecked(true);
					break;
				case 2:
					rb_WeiZhang.setChecked(true);
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

		return view;
	}

	class ZxzcAdapter extends FragmentStatePagerAdapter
	{

		List<Fragment> list;

		public ZxzcAdapter(FragmentManager fm, List<Fragment> list)
		{
			super(fm);
			this.list = list;
		}

		@Override
		public Fragment getItem(int arg0)
		{
			return list.get(arg0);
		}

		@Override
		public int getCount()
		{

			return list.size();
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId)
	{
		if (checkedId == rb_DangQian.getId())
		{
			vp.setCurrentItem(0);
		} else if (checkedId == rb_JieYue.getId())
		{
			vp.setCurrentItem(1);
		} else if (checkedId == rb_WeiZhang.getId())
		{
			vp.setCurrentItem(2);
		}
	}
}
