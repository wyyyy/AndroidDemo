/**   
 * @Title: AsyncTaskUtils.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-15 脧脗脦莽12:43:19 
 * @version V1.0   
 */

package com.example.weatherdemo;

import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author john
 * @createtime 2015-9-15 john
 * @@1.启动任务执行的输入参数
 * @2.后台任务执行的进度
 * @3.后台计算结果的类型
 */
public class AsyncTaskUtils extends
		AsyncTask<Map<String, String>, Object, String>
{

	public Context context;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param applicationContext
	 */

	public AsyncTaskUtils(Context applicationContext)
	{
		context = applicationContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(Map<String, String>... arg0)
	{
		// TODO Auto-generated method stub
		String st = HttpUtils.getRequest();
		// 执行publishProgress()调用onProgressUpdate()方法
		publishProgress(3);
		return st;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 方法用于在取消执行中的任务时更改UI
	 */
	@Override
	protected void onCancelled()
	{
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	@Override
	protected void onCancelled(String result)
	{
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 用于在执行完后台任务后更新UI,显示结果 不能手动调用
	 */
	@Override
	protected void onPostExecute(String result)
	{
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		LayoutInflater factory = LayoutInflater.from(context);
		View iniView = (View) factory.inflate(R.layout.activity_main, null);
		//TextView imageView = (TextView) iniView.findViewById(R.id.txtInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @在execute(Params... params)被调用后立即执行
	 * 
	 * @用于在执行后台任务前做一些UI操作
	 */
	@Override
	protected void onPreExecute()
	{
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 用于更新进度信息 loading...
	 */
	@Override
	protected void onProgressUpdate(Object... progresses)
	{
		// TODO Auto-generated method stub
		super.onProgressUpdate(progresses);
	}

}
