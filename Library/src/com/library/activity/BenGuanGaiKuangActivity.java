package com.library.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BenGuanGaiKuangActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ben_guan_gai_kuang);
	}

	public void onClick1(View v)
	{
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in,
				R.anim.activity_move_out);

	}

}
