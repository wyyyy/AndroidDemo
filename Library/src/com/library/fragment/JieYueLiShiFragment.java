package com.library.fragment;

import com.library.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*
 * 顶部-借阅历史
 */
public class JieYueLiShiFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_jie_yue_li_shi_fragment,
				container, false);
		return view;
	}

}
