package com.library.dao;

import java.util.Map;

import org.apache.http.Header;

import com.loopj.android.http.RequestParams;

public interface IHttpGetData
{
	public String prpUrl();

	public RequestParams prpRequestParams(Map<String, String> pars);

	public Header[] prpHeader();
}
