package com.library.fragment;

import com.library.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeiZhangJiaoKuanFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(
				R.layout.activity_wei_zhang_jiao_kuan_fragment, container,
				false);
		return view;
	}

}
