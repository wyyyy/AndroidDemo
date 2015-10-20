package com.library.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.utils.tools.MxgsaTagHandler;

import com.library.activity.R;
import com.library.bean.EntityZh.RootZhihu;

public class ParaSetActivity extends Activity
{
	private TextView text1, text2;
	RootZhihu rttag = new RootZhihu();
	ImageView imagevew;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		text1 = (TextView) findViewById(R.id.txt_act_detail);
		text2 = (TextView) findViewById(R.id.txt_act_detail_info);

		RootZhihu mPerson = (RootZhihu) getIntent().getSerializableExtra("TAG");
		// eImg = (EntityImg) getIntent().getSerializableExtra("TAGIMG");

		text1.setText(mPerson.getTitle());
		text2.setText(Html.fromHtml(mPerson.getContent(), null, new MxgsaTagHandler(this)));
		text2.setClickable(false);
		// text2.setMovementMethod(LinkMovementMethod.getInstance());
		// imagevew.setImageDrawable(eImg.getDrawable());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
