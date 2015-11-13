package com.library.fragment;

import com.library.activity.R;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.utils.sqlhelp.SqlHelper;

/*
 * 底部3.抢座
 */
public class QiangZuoFragment3 extends Fragment {
	View view;
	Button btnQuery;
	TextView txtQuery;
	ListView listView;
	Toast tst;
	SqlHelper dbHelper;
AdapterQiangZuo myAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.fragment_qiangzuo, container, false);
		initView();
		initData();
		return view;
	}

	void initView()
	{
		txtQuery = (TextView) view.findViewById(R.id.txt_f_querycontent);
		listView=(ListView) view.findViewById(R.id.list_f_qz_display);
		btnQuery = (Button) view.findViewById(R.id.btn_f_query);
		
		btnQuery.setOnClickListener(new BtnClickListener());
	}

	private void initData()
	{
		dbHelper = new SqlHelper(getActivity());
		long lg = dbHelper.insert("", "");
		Toast.makeText(getActivity(), "lg:" + lg, Toast.LENGTH_SHORT).show();
	}

	class BtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v)
		{

			StringBuilder sb = new StringBuilder();

			Cursor cursor = dbHelper.select();
			while (cursor.moveToNext())
			{
				int personid = cursor.getInt(0); // 获取第一列的值,第一列的索引从0开始
				sb = new StringBuilder();
				sb.append(personid);
				Toast.makeText(getActivity(), "ID:" + sb, Toast.LENGTH_SHORT).show();
			}
			cursor.close();
		}
	}
}
