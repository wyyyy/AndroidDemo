package cn.utis.AsyncUtils;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class myAsyncHttpResponseHandler extends AsyncHttpResponseHandler
{

	@Override
	public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish()
	{
		// TODO Auto-generated method stub
		super.onFinish();
	}

	@Override
	public void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
	{
		// TODO Auto-generated method stub

	}

}
