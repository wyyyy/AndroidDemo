package com.library.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.library.adapter.ViewPagerAdapter;

//点的改变应该根据ViewPager中的View来进行改变,监听ViewPager的改变
public class SplashActivity extends Activity implements OnPageChangeListener
{

	private ViewPager vp;
	// 适配器
	private ViewPagerAdapter vpAdapter;

	private List<View> views;
	// 对于点的操作
	@SuppressWarnings("unused")
	private ImageView[] dots;

	// private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3};

	// 在ViewPager的最后一个页面加上Button以提示用户进入程序
	private Button start_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initViews();
		// initDots();
	}

	// 给Views添加图片
	@SuppressLint("InflateParams") private void initViews()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.one, null));
		views.add(inflater.inflate(R.layout.two, null));
		views.add(inflater.inflate(R.layout.three, null));

		vpAdapter = new ViewPagerAdapter(this, views);

		vp = (ViewPager) this.findViewById(R.id.viewPager);
		vp.setAdapter(vpAdapter);

		// 得到第三个页面的Button
		start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
		start_btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
				SplashActivity.this.overridePendingTransition(
						R.anim.activity_move_in_start,
						R.anim.activity_move_out_start);
				// 当前页面不在需要了
				finish();
			}
		});

		vp.setOnPageChangeListener(this);
	}

	/*
	 * //在ViewPager上添加页面滑动的导航点，用以表示当前ViewPager的索引 private void initDots() {
	 * dots=new ImageView[views.size()]; for(int i=0;i<views.size();i++) {
	 * dots[i]=(ImageView)findViewById(ids[i]); } }
	 */

	// 当滑动状态改变的时候调用
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub

	}

	// 当页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

	// 当前新的页面被选中的时候调用
	@Override
	public void onPageSelected(int arg0)
	{
		/*
		 * for(int i=0;i<ids.length;i++) { //如果当前页面被选中 if(arg0==i) {
		 * dots[i].setImageResource(R.drawable.shoucang_2); }else {
		 * dots[i].setImageResource(R.drawable.shoucang_1);
		 * 
		 * } }
		 */
	}

}
