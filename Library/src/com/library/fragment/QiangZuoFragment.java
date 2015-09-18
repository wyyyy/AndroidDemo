package com.library.fragment;

import com.library.activity.R;
import com.library.view.AddPopWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/*
 * 底部3.抢座
 */
public class QiangZuoFragment extends Fragment {

	private View parentView;
	private ImageView iv_add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_dongtai, container,
				false);
		iv_add = (ImageView) parentView.findViewById(R.id.iv_add);

		// 点击右边显示
		iv_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddPopWindow addPopWindow = new AddPopWindow(getActivity());
				addPopWindow.showPopupWindow(iv_add);
			}

		});

		return parentView;
	}

}
