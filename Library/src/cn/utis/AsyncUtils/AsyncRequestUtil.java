package cn.utis.AsyncUtils;

import java.util.Map;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncRequestUtil
{
	private volatile static AsyncRequestUtil instance;
	private static AsyncHttpClient client;

	private AsyncRequestUtil()
	{
	}

	/** Returns singleton class instance */
	public static AsyncHttpClient getAsyInstance()
	{
		if (client == null)
		{
			synchronized (AsyncHttpClient.class)
			{
				if (client == null)
				{
					client = new AsyncHttpClient();
				}
			}
		}
		return client;
	}

	public static AsyncRequestUtil getInstance()
	{
		if (instance == null)
		{
			synchronized (AsyncRequestUtil.class)
			{
				if (instance == null)
				{
					instance = new AsyncRequestUtil();
				}
			}
		}
		return instance;
	}

	// public static AsyncHttpClient client = new AsyncHttpClient();

	@SuppressWarnings("unused")
	private void getQuest(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		client.get(getAbsoluteUrl(url), params, new AsyncHttpResponseHandler()
		{

			@Override
			public void onStart()
			{
				// called before request is started
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response)
			{
				// called when response HTTP status is "200 OK"
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable err)
			{
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
			}

			@Override
			public void onRetry(int retryNo)
			{
				// called when request is retried
			}
		});
	}

	public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private String getAbsoluteUrl(String relativeUrl)
	{
		return relativeUrl;
	}

	public RequestParams PrpRequestParams(Map<String, String> reqParsms)
	{
		RequestParams params = new RequestParams();
		params.put("username", "james");
		params.put("password", "123456");
		return params;

	}
}
