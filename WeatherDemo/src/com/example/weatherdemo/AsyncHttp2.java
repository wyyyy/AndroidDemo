/**   
 * @Title: AsyncHttp2.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-15 ÉÏÎç10:49:15 
 * @version V1.0   
 */

package com.example.weatherdemo;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author john Android-Async-Http
 * @createtime 2015-9-15 john
 */
public class AsyncHttp2
{
	public StringBuffer sb = new StringBuffer();

	public void get(String url, RequestParams params,
			AsyncHttpResponseHandler rHandler)
	{

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.baidu.com", null, new AsyncHttpResponseHandler()
		{

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] response)
			{
				System.out.println(statusCode);
				// TODO Auto-generated method stub
				if (statusCode == 200)
				{
					sb.append(statusCode);
				}

			}
		});

	}
}
