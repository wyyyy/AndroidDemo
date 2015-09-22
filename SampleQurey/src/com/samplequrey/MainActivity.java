package com.samplequrey;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener,
		OnCheckedChangeListener
{

	//
	private TextView text1, text2, text3;

	private boolean is_closed = false;
	private long mExitTime;

	private RadioGroup rg;
	private RadioButton rb1, rb2, rb3, rb4, rb5;

	//
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initiView();
	}

	private void initiView()
	{
		rg = (RadioGroup) findViewById(R.id.rg);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rb5 = (RadioButton) findViewById(R.id.rb5);
		rg.setOnCheckedChangeListener(this);
		// 之前是rb1
		rb2.setChecked(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		if (rb1.getId() == checkedId)
		{
			changeFragment(new HuDongFragment());
		} else if (rb2.getId() == checkedId)
		{
			// changeFragment(new HuDongFragment());
		} else if (rb3.getId() == checkedId)
		{
			// changeFragment(new HuDongFragment());
		} else if (rb4.getId() == checkedId)
		{
			// changeFragment(new HuDongFragment());
		} else if (rb5.getId() == checkedId)
		{
			// (new HuDongFragment());
		}

	}

	private void changeFragment(Fragment targetFragment)
	{
		// resideMenu.clearIgnoredViewList();
		/*
		 * getSupportFragmentManager().beginTransaction()
		 * .replace(R.id.main_fragment, targetFragment, "fragment")
		 * .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		 * .commit();
		 */

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		HuDongFragment fragment = new HuDongFragment();

		fragmentTransaction.add(R.id.main_fragment, fragment);
		fragmentTransaction.commit();

	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub

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
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
