package com.samplequrey.httpdata;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GetKaifangData
{
	String baseURl = "http://api.sheyun.org/api.php";

	public	void getKaifang()
	{
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params = prpHttpParams();
		client.post(baseURl, params, responseHandler);
	}

	AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler()
	{

		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(int statusCode, Header[] headers, byte[] response)
		{
			// TODO Auto-generated method stub
			System.out.println("ok;" + statusCode);
			System.out.println(response);
		}
	};

	private RequestParams prpHttpParams()
	{
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.put("so", "80852966");
		return params;
	}
}
