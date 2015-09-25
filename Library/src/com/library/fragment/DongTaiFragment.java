package com.library.fragment;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.library.activity.R;
import com.library.data.HttpClientHelp;

public class DongTaiFragment extends Fragment implements
		OnCheckedChangeListener
{
	private Button btnQuerykf;
	OnClickListener btnlist;
	private TextView txtView;
	private EditText eiitInfo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View contentView = inflater.inflate(R.layout.activity_dong_tai,
				container, false);
		btnQuerykf = (Button) contentView.findViewById(R.id.btnkf);
		eiitInfo = (EditText) contentView.findViewById(R.id.editText1);
		txtView = (TextView) contentView.findViewById(R.id.txtInfo);
		btnQuerykf.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				try
				{
					txtView.setText("");

					new Thread(runnable).start();

				} catch (Exception e)
				{
					System.err.println(e);
				}

			}
		});
		return contentView;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@SuppressLint("HandlerLeak") private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 200:
				String result = (String) msg.getData().get("value");
				txtView.setText(result);
				break;
			case 404:
				txtView.setText(404 + "");
				break;

			default:
				break;
			}

		}
	};
	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			// TODO: http request.
			Message msg = new Message();
			Bundle data = new Bundle();

			if (eiitInfo.getText().toString().trim() == null)
			{
				// Toast.makeText(content, "值为空，请重新输入！",
				// Toast.LENGTH_SHORT).show();
				msg.what = 404;
				return;
			}
			String baseURlPost = "http://sheyun.org/kf.php";
			String st = "";
			try
			{
				HashMap<String, String> mappars = new HashMap<String, String>();

				mappars.put("vip", "1");
				// mappars.put("search", eiitInfo.getText().toString().trim());
				mappars.put("search", "140103196706185119");

				st = HttpClientHelp.sendPost(baseURlPost, mappars);
				msg.what = 200;
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.what = 404;
				st = "";
			}
			;
			data.putString("value", SubReString(st));
			msg.setData(data);

			handler.sendMessage(msg);
		}
	};

	private String SubReString(String str)
	{
		String rtstr = "";
		int start = 0;
		String string = "tr class=\"Odd\">";
		start = str.indexOf(string);
		str = str.substring(start);
		start = str.indexOf("<" + string);
		str = str.substring(start);
		rtstr = str;
		return rtstr;
	}
}
