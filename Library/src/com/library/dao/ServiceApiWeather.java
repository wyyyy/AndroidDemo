package com.library.dao;

import java.util.Map;

import org.apache.http.Header;

import com.loopj.android.http.RequestParams;

/*
 * 1.prp pars
 * 2.pro url
 * 3.prp header if need
 */
public class ServiceApiWeather implements IHttpGetData
{
	String url = "";
	Map<String, String> pars;
	Map<String, String> mYHeader;

	public ServiceApiWeather()
	{

	}

	public ServiceApiWeather(String _url, Map<String, String> _pars, Map<String, String> _mYHeader)
	{
		url = _url;
		pars = _pars;
		mYHeader = _mYHeader;
	}

	@Override
	public String prpUrl()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestParams prpRequestParams(Map<String, String> pars)
	{
		RequestParams params = new RequestParams();
		params.put("format", "2");
		params.put("cityname", "");
		params.put("key", "ee37c488ea30af4dbb1d4a5a997d821c");
		return params;
	}

	@Override
	public Header[] prpHeader()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
