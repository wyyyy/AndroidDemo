package com.library.fragment;

import com.library.activity.R;
import com.library.activity.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class DangQianJieYueFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.activity_dang_qian_jie_yue_fragment, container, false);
		return view;
	}

}
