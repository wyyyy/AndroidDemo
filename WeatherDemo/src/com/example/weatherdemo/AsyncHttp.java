/**   
 * @Title: AsyncHttp.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 ����7:17:05 
 * @version V1.0   
 */

package com.example.weatherdemo;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author john
 * @createtime 2015-9-14 john
 */
public class AsyncHttp
{
	private Context _context;

	public AsyncHttp(Context context)
	{
		_context = context;
	}

	private static final String BASE_URL = "http://wthrcdn.etouch.cn/weather_mini";
	static StringBuffer sb = new StringBuffer();
	static AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler()
	{

		@Override
		public void onSuccess(int statusCode, Header[] headers, byte[] response)
		{
			// // called when response HTTP status is "200 OK"
			// Toast.makeText(_context,
			// response.getString("result"),
			// Toast.LENGTH_LONG).show();
			if (statusCode == 200)
			{
				sb.append(GzipUtils.unGZip(response));
			}

		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3)
		{
			// TODO Auto-generated method stub

		}
	};
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler rHandler)
	{

		params = new RequestParams();
		params.put("city", "%E4%B8%8A%E6%B5%B7");
		params.put("callback", "flightHandler");
		params.put("callback", "flightHandler");
		params.put("_", System.currentTimeMillis() / 10000L + "");

		String strtest = "http://www.baidu.com";
		client.get(BASE_URL, params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler)
	{
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl)
	{
		return BASE_URL + relativeUrl;
	}

}
