package com.library.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class KaiFangShiJianActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kai_fang_shi_jian);
	}

	public void onClick5(View v)
	{
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in,
				R.anim.activity_move_out);

	}

}
