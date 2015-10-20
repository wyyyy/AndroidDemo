package com.library.dao;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.bean.ApiWeather.ApiWeatherRoot;
import com.library.data.AsyncHttpClientHelp;

public class ServiceApiWeather implements IHttpGetData
{
	AsyncHttpClientHelp help = new AsyncHttpClientHelp();
	ApiWeatherRoot rtRoot = new ApiWeatherRoot();
	String rtStr = "";

	@Override
	public void getData(String url, Map<String, String> pars)
	{
		long startTime = System.currentTimeMillis();

		help = new AsyncHttpClientHelp();
		// TODO Auto-generated method stub
		// https://www.juhe.cn/docs/api/id/39
		// key=ee37c488ea30af4dbb1d4a5a997d821c
		String strKey = "ee37c488ea30af4dbb1d4a5a997d821c";
		String strCityName = "上海";
		url = "http://v.juhe.cn/weather/index?format=2&cityname=" + strCityName + "&key=" + strKey;
		rtStr = help.SendGet(url, null);
		long endTime = System.currentTimeMillis();
		System.out.println("time;" + (endTime - startTime) + "ms");
	}

	public ApiWeatherRoot Str2Beans(String strConten)
	{
		getData("", null);
		rtRoot = new Gson().fromJson(rtStr, new TypeToken<ApiWeatherRoot>()
		{
		}.getType());

		return rtRoot;
	}
}
