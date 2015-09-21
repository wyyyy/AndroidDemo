package com.library.data;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class GetKaifangData
{

	public GetKaifangData()
	{

	}

	private static final int READ_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;

	public static String executeGet(String urlString) throws Exception
	{
		InputStream inputStream = executeGetInputStream(urlString);
		ByteArrayOutputStream outputStream = null;
		try
		{
			outputStream = new ByteArrayOutputStream(1024);
			int readLength = 0;
			byte[] buffer = new byte[4 * 1024];
			while ((readLength = inputStream.read(buffer)) > 0)
			{
				outputStream.write(buffer, 0, readLength);
			}
			String json = outputStream.toString("utf-8");
			return json;
		} finally
		{
			if (inputStream != null)
				inputStream.close();
			if (outputStream != null)
			{
				outputStream.close();
			}
		}
	}

	public static InputStream executeGetInputStream(String urlString)
			throws Exception
	{
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 指的是与请求网址的服务器建立连接的超时时间。
		connection.setConnectTimeout(CONNECT_TIMEOUT);
		// 指的是建立连接后如果指定时间内服务器没有返回数据的后超时。
		connection.setReadTimeout(READ_TIMEOUT);
		connection.setRequestMethod("GET");
		int code = connection.getResponseCode();
		if (code >= 200 && code <= 299)// 连接成功,返回结果
		{
			return connection.getInputStream();
		} else
		{
			String info = "executeGet network error urlString:" + urlString
					+ " code:" + code;
			throw new Exception(info);
		}
	}

	public static String executePost(String urlString, String strPars)
			throws Exception
	{
		NameValuePair pair1 = new BasicNameValuePair("search", strPars);
		NameValuePair pair2 = new BasicNameValuePair("vip", "1");

		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);
		HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
		// URL使用基本URL即可，其中不需要加参数
		HttpPost httpPost = new HttpPost(urlString);
		// 将请求体内容加入请求中
		httpPost.setEntity(requestHttpEntity);
		// 需要客户端对象来发送请求
		HttpClient httpClient = new DefaultHttpClient();
		// 发送请求
		HttpResponse response = httpClient.execute(httpPost);
		// 显示响应
		return showResponseResult(response);
	}

	private static String showResponseResult(HttpResponse response)
	{
		String result = "";
		if (null == response)
		{
			return result;
		}

		HttpEntity httpEntity = response.getEntity();
		try
		{
			InputStream inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));

			String line = "";
			while (null != (line = reader.readLine()))
			{
				result += line;

			}

			System.out.println(result);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
