/**   
 * @Title: AsyncTaskUtils.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-15 ÏÂÎç12:43:19 
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
 * @@1.��������ִ�е��������
 * @2.��̨����ִ�еĽ���
 * @3.��̨������������
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
		// ִ��publishProgress()����onProgressUpdate()����
		publishProgress(3);
		return st;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ����������ȡ��ִ���е�����ʱ����UI
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
	 * @see ������ִ�����̨��������UI,��ʾ��� �����ֶ�����
	 */
	@Override
	protected void onPostExecute(String result)
	{
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		LayoutInflater factory = LayoutInflater.from(context);
		View iniView = (View) factory.inflate(R.layout.activity_main, null);
		// TextView imageView = (TextView) iniView.findViewById(R.id.txtInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @��execute(Params... params)�����ú�����ִ��
	 * 
	 * @������ִ�к�̨����ǰ��һЩUI����
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
	 * @see ���ڸ��½�����Ϣ loading...
	 */
	@Override
	protected void onProgressUpdate(Object... progresses)
	{
		// TODO Auto-generated method stub
		super.onProgressUpdate(progresses);
	}

}
