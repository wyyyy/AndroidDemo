package com.library.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.library.fragment.FaXianFragment5;
import com.library.fragment.HuDongFragment1;
<<<<<<< HEAD
import com.library.fragment.JieYueFragment2;
import com.library.fragment.QiangZuoFragment3;
import com.library.fragment.XinShengDaoHangFragment4;
=======
import com.library.fragment.JieYueFragment;
import com.library.fragment.QiangZuoFragment32;
import com.library.fragment.XinShengDaoHangFragment;
>>>>>>> f64752851643cd504af77dab31a8f515f90d8d20
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuInfo;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnCheckedChangeListener
{

	public ResideMenu resideMenu;

	private ResideMenuItem itemGuanZhangEmail;
	private ResideMenuItem itemGrRenXinXi;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;
	Fragment mContent = new Fragment();
	private ResideMenuInfo info;
	List<Fragment> lsitFragment = new ArrayList<Fragment>();
	List<FraList> flist = new ArrayList<FraList>();
	List<FraList> flistSAll = new ArrayList<FraList>();
	List<String> lsitString = new ArrayList<String>();;
	@SuppressWarnings("unused")
	private TextView text1, text2, text3;
	Map<String, Fragment> map = new HashMap<String, Fragment>();
	private boolean is_closed = false;
	private long mExitTime;
	HuDongFragment1 fhd;
	XinShengDaoHangFragment4 fxs;
	JieYueFragment2 fjjy;
	QiangZuoFragment3 fqz;
	FaXianFragment5 ffaxian;
	private RadioGroup rg;
	private RadioButton rb1, rb2, rb3, rb4, rb5;
	FragmentManager fManager;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fManager = getSupportFragmentManager();
		setUpMenu();
		setListener();
	}

	private void setUpMenu()
	{

		rg = (RadioGroup) findViewById(R.id.rg);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rb5 = (RadioButton) findViewById(R.id.rb5);
		rg.setOnCheckedChangeListener(this);
		rb2.setChecked(true);
		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// disable right menu
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemGuanZhangEmail = new ResideMenuItem(this, R.drawable.icon_profile, "馆长信箱");
		itemGrRenXinXi = new ResideMenuItem(this, R.string.MenuyInfo);
		itemZhuangban = new ResideMenuItem(this, R.string.Menudqck);
		itemShoucang = new ResideMenuItem(this, R.string.MenuJyjl);
		itemXiangce = new ResideMenuItem(this, "预约查询");
		itemFile = new ResideMenuItem(this, "违章查询");

		resideMenu.addMenuItem(itemGuanZhangEmail, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemGrRenXinXi, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemZhuangban, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemShoucang, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemXiangce, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);
		// 姓名和学号
		info = new ResideMenuInfo(this, R.drawable.icon_profile, "我我我", "2012012147");

	}

	private void setListener()
	{
		resideMenu.addMenuInfo(info);
		itemGuanZhangEmail.setOnClickListener(this);
		itemGrRenXinXi.setOnClickListener(this);
		itemZhuangban.setOnClickListener(this);
		itemShoucang.setOnClickListener(this);
		itemXiangce.setOnClickListener(this);
		itemFile.setOnClickListener(this);
		info.setOnClickListener(this);
	}

	// 点击按钮显示左边侧滑栏
	public void onClickLiftMenu(View v)
	{
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view)
	{
		if (view == itemGuanZhangEmail)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "ss");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemGrRenXinXi)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "sw");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemZhuangban)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "aa");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemShoucang)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "bb");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemXiangce)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "cc");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemFile)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "dd");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == info)
		{
			Intent intent = new Intent();
			intent.putExtra("flog", "ee");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener()
	{
		@Override
		public void openMenu()
		{
			is_closed = false;
			// leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu()
		{
			is_closed = true;
			// leftMenu.setVisibility(View.VISIBLE);
		}
	};

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu()
	{
		return resideMenu;
	}

	// 监听手机上的BACK键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			// 判断菜单是否关闭
			if (is_closed)
			{
				// 判断两次点击的时间间隔（默认设置为2秒）
				if ((System.currentTimeMillis() - mExitTime) > 2000)
				{
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

					mExitTime = System.currentTimeMillis();
				} else
				{
					finish();
					System.exit(0);
					super.onBackPressed();
				}
			} else
			{
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId)
	{
		int idex = 0;
		if (rb1.getId() == checkedId)
		{
			idex = 1;
		} else if (rb2.getId() == checkedId)
		{
			idex = 2;
		} else if (rb3.getId() == checkedId)
		{
			idex = 3;
		} else if (rb4.getId() == checkedId)
		{
			idex = 4;
		} else if (rb5.getId() == checkedId)
		{
			idex = 5;
		}
		setChioceItem(idex);
	}

	public void setChioceItem(int index)
	{
		if (fManager == null)
			return;
		// reset item and invisible allFragment
		FragmentTransaction transaction = fManager.beginTransaction();
		hideFragments(transaction);
		switch (index)
		{
		case 1:
			if (fhd == null)
			{
				fhd = new HuDongFragment1();
				transaction.add(R.id.main_fragment, fhd);
			} else
			{
				transaction.show(fhd);
			}
			break;

		case 2:
			if (fjjy == null)
			{
				fjjy = new JieYueFragment2();
				transaction.add(R.id.main_fragment, fjjy);
			} else
			{
				transaction.show(fjjy);
			}
			break;
		case 3:
			if (fqz == null)
			{
				fqz = new QiangZuoFragment3();
				transaction.add(R.id.main_fragment, fqz);
			} else
			{
				transaction.show(fqz);
			}
			break;
		case 4:

			if (fxs == null)
			{
				fxs = new XinShengDaoHangFragment4();
				transaction.add(R.id.main_fragment, fxs);
			} else
			{
				transaction.show(fxs);
			}

			break;
		case 5:
			if (ffaxian == null)
			{
				ffaxian = new FaXianFragment5();
				transaction.add(R.id.main_fragment, ffaxian);
			} else
			{
				transaction.show(ffaxian);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction)
	{

		if (fhd != null)
		{
			transaction.hide(fhd);
		}
		if (fxs != null)
		{
			transaction.hide(fxs);
		}
=======
		} else if (rb5.getId() == checkedId)
		{
			idex = 5;
		}
		setChioceItem(idex);
	}

	public void setChioceItem(int index)
	{
		if (fManager == null)
			return;
		// reset item and invisible allFragment
		FragmentTransaction transaction = fManager.beginTransaction();
		hideFragments(transaction);
		switch (index)
		{
		case 1:
			if (fhd == null)
			{
				fhd = new HuDongFragment1();
				transaction.add(R.id.main_fragment, fhd);
			} else
			{
				transaction.show(fhd);
			}
			break;

		case 2:

			if (fxs == null)
			{
				fxs = new XinShengDaoHangFragment();
				transaction.add(R.id.main_fragment, fxs);
			} else
			{
				transaction.show(fxs);
			}

			break;
		case 3:
			if (fjjy == null)
			{
				fjjy = new JieYueFragment();
				transaction.add(R.id.main_fragment, fjjy);
			} else
			{
				transaction.show(fjjy);
			}
			break;
		case 4:
			if (fqz == null)
			{
				fqz = new QiangZuoFragment32();
				transaction.add(R.id.main_fragment, fqz);
			} else
			{
				transaction.show(fqz);
			}
			break;

		case 5:
			if (ffaxin == null)
			{
				ffaxin = new FaXianFragment5();
				transaction.add(R.id.main_fragment, ffaxin);
			} else
			{
				transaction.show(ffaxin);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction)
	{

		if (fhd != null)
		{
			transaction.hide(fhd);
		}
		if (fxs != null)
		{
			transaction.hide(fxs);
		}
>>>>>>> f64752851643cd504af77dab31a8f515f90d8d20
		if (fjjy != null)
		{
			transaction.hide(fjjy);
		}
		if (fqz != null)
		{
			transaction.hide(fqz);
		}
<<<<<<< HEAD
		if (ffaxian != null)
		{
			transaction.hide(ffaxian);
=======
		if (ffaxin != null)
		{
			transaction.hide(ffaxin);
>>>>>>> f64752851643cd504af77dab31a8f515f90d8d20
		}

	}
}
