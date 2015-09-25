package com.library.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/*
 * Apace接口(org.appache.http)
 */
public class HttpClientHelp
{
	@SuppressWarnings("unused")
	private HttpClient getHttpClient()
	{

		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
		HttpParams httpParams;
		HttpClient httpClient;
		httpParams = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小

		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		// 设置重定向，缺省为 true

		HttpClientParams.setRedirecting(httpParams, true);

		// 设置 user agent

		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// 创建一个 HttpClient 实例

		// 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient

		// 中的用法，在 Android 1.5 中我们需要使用 Apace 的缺省实现 DefaultHttpClient

		httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}

	/*
	 * post
	 */
	public static String sendPost(String uriAPI, HashMap<String, String> mappars)
	{
		String strResult = "";
		/* 建立HTTP Post连线 */
		HttpPost httpRequest = new HttpPost(uriAPI);
		// Post运作传送变数必须用NameValuePair[]阵列储存
		// 传参数 服务端获取的方法为request.getParameter("name")
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : mappars.keySet())
		{
			String keyvalue = mappars.get(key);
			params.add(new BasicNameValuePair(key, keyvalue));
		}

		try
		{

			// 发出HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 取得HTTP response
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);

			// 若状态码为200 OK
			if (httpResponse.getStatusLine().getStatusCode() == 200)
			{
				// 取出回应字串
				strResult = EntityUtils.toString(httpResponse.getEntity(),
						HTTP.UTF_8);
			} else
			{
				strResult = httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return strResult;
	}

	@SuppressWarnings("rawtypes")
	public String sendGet(String baseUrl, Map params)
	{

		/* 建立HTTPGet对象 */

		String paramStr = "";

		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext())
		{
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			paramStr += paramStr = "&" + key + "=" + val;
		}

		if (!paramStr.equals(""))
		{
			paramStr = paramStr.replaceFirst("&", "?");
			baseUrl += paramStr;
		}
		HttpGet httpRequest = new HttpGet(baseUrl);

		String strResult = "doGetError";

		try
		{
			HttpClient httpClient = new DefaultHttpClient();

			/* 发送请求并等待响应 */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200)
			{
				/* 读返回数据 */
				strResult = EntityUtils.toString(httpResponse.getEntity());

			} else
			{
				strResult = "Error Response: "
						+ httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e)
		{
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (IOException e)
		{
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (Exception e)
		{
			strResult = e.getMessage().toString();
			e.printStackTrace();
		}

		return strResult;
	}
}
