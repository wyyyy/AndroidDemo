package com.library.fragment;

import org.apache.http.Header;

import android.os.Bundle;
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
import android.widget.Toast;
import cn.utis.AsyncUtils.AsyncRequestUtil;

import com.google.gson.Gson;
import com.library.activity.R;
import com.library.application.UrlUtils;
import com.library.bean.ApiWeather.ApiWeatherRoot;
import com.library.dao.ServiceApiWeather;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/*
 * for api weather
 */
public class DongTaiFragment12 extends Fragment implements OnCheckedChangeListener
{
	private Button btnQuerykf;
	OnClickListener btnlist;
	private TextView txtView;
	private EditText editInfo;
	ServiceApiWeather daoService = new ServiceApiWeather();
	ApiWeatherRoot rtRoot = new ApiWeatherRoot();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View contentView = inflater.inflate(R.layout.activity_dong_tai, container, false);
		btnQuerykf = (Button) contentView.findViewById(R.id.btnkf);
		editInfo = (EditText) contentView.findViewById(R.id.editText1);
		txtView = (TextView) contentView.findViewById(R.id.txtInfo);
		btnQuerykf.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					txtView.setText("");
					if (editInfo.getText().toString().trim() == "")
					{
						Toast.makeText(getActivity(), "查询城市不能为空", Toast.LENGTH_LONG).show();
						return;
					} else
					{
						getData(editInfo.getText().toString().trim());
					}
				} catch (Exception e)
				{
					System.err.println(e);
					Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
		return contentView;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1)
	{

	}

	void getData(String strCity)
	{
		RequestParams params = new RequestParams();
		params.put("format", "2");
		params.put("cityname", strCity);
		params.put("key", "ee37c488ea30af4dbb1d4a5a997d821c");
		AsyncRequestUtil.getAsyInstance().get(UrlUtils.urlApiWeather, params, new TextHttpResponseHandler()
		{

			@Override
			public void onSuccess(int statusCode, Header[] header, String strResponse)
			{
				if (strResponse == "")
				{
					Toast.makeText(getActivity(), "NO VALUE", Toast.LENGTH_LONG).show();
				} else
				{
					rtRoot = new Gson().fromJson(strResponse, ApiWeatherRoot.class);
					Toast.makeText(getActivity(), rtRoot.getResult().getToday().getTemperature(), Toast.LENGTH_LONG)
							.show();
				}

			}

			@Override
			public void onFailure(int statusCode, Header[] header, String strResponse, Throwable arg3)
			{
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)

			}
		});
	}
}
