package com.library.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class LiShiYanGeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_li_shi_yan_ge);
	}

	public void onClick2(View v) {
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in,
				R.anim.activity_move_out);

	}

}
