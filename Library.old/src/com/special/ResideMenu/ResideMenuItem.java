package com.special.ResideMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.activity.R;

/**
 * User: special Date: 13-12-10 Time: 下午11:05 Mail: specialcyci@gmail.com
 */
public class ResideMenuItem extends LinearLayout
{

	/** menu item icon */
	private ImageView iv_icon;
	/** menu item title */
	private TextView tv_title;
	private TextView tv_title_guanzhang;

	private LinearLayout linner1, linner2;

	public ResideMenuItem(Context context)
	{
		super(context);
		initViews(context);
	}

	public ResideMenuItem(Context context, String title)
	{
		super(context);
		initViews(context);
		linner2.setVisibility(View.VISIBLE);
		tv_title.setText(title);
	}

	// 新增的WZH
	public ResideMenuItem(Context context, int title)
	{
		super(context);
		initViews(context);
		linner2.setVisibility(View.VISIBLE);
		tv_title.setText(title);
	}

	public ResideMenuItem(Context context, int icon, int title)
	{
		super(context);
		initViews(context);
		iv_icon.setImageResource(icon);
		tv_title.setText(title);
	}

	public ResideMenuItem(Context context, int icon, String title)
	{
		super(context);
		initViews(context);
		linner1.setVisibility(View.VISIBLE);
		iv_icon.setImageResource(icon);
		tv_title_guanzhang.setText(title);
	}

	private void initViews(Context context)
	{
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.residemenu_item, this);
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title_guanzhang = (TextView) findViewById(R.id.tv_titleguanzhang);
		linner1 = (LinearLayout) findViewById(R.id.linner1);
		linner2 = (LinearLayout) findViewById(R.id.linner2);

	}

	/**
	 * set the icon color;
	 * 
	 * @param icon
	 */
	public void setIcon(int icon)
	{
		iv_icon.setImageResource(icon);
	}

	/**
	 * set the title with resource ;
	 * 
	 * @param title
	 */
	public void setTitle(int title)
	{
		tv_title.setText(title);
	}

	/**
	 * set the title with string;
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		tv_title.setText(title);
	}

	public TextView getTv_title_guanzhang()
	{
		return tv_title_guanzhang;
	}

	public void setTv_title_guanzhang(TextView tv_title_guanzhang)
	{
		this.tv_title_guanzhang = tv_title_guanzhang;
	}

}
