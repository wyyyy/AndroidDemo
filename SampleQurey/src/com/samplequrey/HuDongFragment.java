package com.samplequrey;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.samplequrey.httpdata.GetKaifangData;

public class HuDongFragment extends Fragment
{
	private View contentView;
	private ViewPager vp;
	private Button btnQuerykf;
	OnClickListener btnlist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		contentView = inflater.inflate(R.layout.frag_querykf, container, false);
		if (contentView == null)
		{
			return contentView;
		}
		// TODO Auto-generated method stub
		btnQuerykf = (RadioButton) contentView.findViewById(R.id.btnkf);
		// vp = (ViewPager) contentView.findViewById(R.id.viewpagerHuDong);
		btnQuerykf.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				GetKaifangData getData = new GetKaifangData();
				// getData.getKaifang();
			}
		});
		return contentView;
	}

}
