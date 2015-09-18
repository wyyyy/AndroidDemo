package com.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.library.fragment.FaXianFragment;

import com.library.fragment.JieYueFragment;
import com.library.fragment.QiangZuoFragment;
import com.library.fragment.HuDongFragment;
import com.library.fragment.XinShengDaoHangFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuInfo;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener, OnCheckedChangeListener {

	public ResideMenu resideMenu;

	private ResideMenuItem itemGuanZhangEmail;
	private ResideMenuItem itemGrRenXinXi;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;

	private ResideMenuInfo info;

	private TextView text1, text2, text3;

	private boolean is_closed = false;
	private long mExitTime;

	private RadioGroup rg;
	private RadioButton rb1, rb2, rb3, rb4, rb5;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setUpMenu();
		// changeFragment(new HuDongFragment());
		setListener();
	}

	private void setUpMenu() {

		rg = (RadioGroup) findViewById(R.id.rg);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rb5 = (RadioButton) findViewById(R.id.rb5);
		rg.setOnCheckedChangeListener(this);
		//之前是rb1
		rb2.setChecked(true);

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// 禁止使用右侧菜单
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemGuanZhangEmail = new ResideMenuItem(this, R.drawable.icon_profile,
				"馆长信箱");
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
		info = new ResideMenuInfo(this, R.drawable.icon_profile, "我我我",
				"2012012147");

	}

	private void setListener() {
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
	public void onClickLiftMenu(View v) {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		if (view == itemGuanZhangEmail) {
			Intent intent = new Intent();
			intent.putExtra("flog", "ss");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemGrRenXinXi) {
			Intent intent = new Intent();
			intent.putExtra("flog", "sw");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemZhuangban) {
			Intent intent = new Intent();
			intent.putExtra("flog", "aa");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemShoucang) {
			Intent intent = new Intent();
			intent.putExtra("flog", "bb");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemXiangce) {
			Intent intent = new Intent();
			intent.putExtra("flog", "cc");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == itemFile) {
			Intent intent = new Intent();
			intent.putExtra("flog", "dd");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		} else if (view == info) {
			Intent intent = new Intent();
			intent.putExtra("flog", "ee");
			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			// leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu() {
			is_closed = true;
			// leftMenu.setVisibility(View.VISIBLE);
		}
	};

	private void changeFragment(Fragment targetFragment) {
		// resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// 监听手机上的BACK键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				// 判断两次点击的时间间隔（默认设置为2秒）
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
					super.onBackPressed();
				}
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {

		if (rb1.getId() == checkedId) {
			changeFragment(new HuDongFragment());
		} else if (rb2.getId() == checkedId) {
			changeFragment(new JieYueFragment());
		} else if (rb3.getId() == checkedId) {
			changeFragment(new QiangZuoFragment());
		} else if (rb4.getId() == checkedId) {
			changeFragment(new XinShengDaoHangFragment());
		} else if (rb5.getId() == checkedId) {
			changeFragment(new FaXianFragment());
		}
	}
}
