/**   
 * @Title: HttpUtils.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 下午9:47:42 
 * @version V1.0   
 */

package com.example.weatherdemo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * @author john
 * @createtime 2015-9-14 john
 */
public class HttpUtils
{
	/**
	 * 对网络连接状态进行判断
	 * 
	 * @return true, 可用； false， 不可用
	 */
	public static boolean isOpenNetwork(Context context)
	{
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager.getActiveNetworkInfo() != null)
		{
			return connManager.getActiveNetworkInfo().isAvailable();
		}

		return false;
	}

	public static String getRequest()
	{

		StringBuffer sb = new StringBuffer();
		String baseURL = "http://wthrcdn.etouch.cn/weather_mini";
		String city = "%E4%B8%8A%E6%B5%B7";
		long time = DataUtils.GetSystemCurrentTimeMillis();

		// 使用GET方法发送请求,需要把参数加在URL后面，用？连接，参数之间用&分隔
		String url = baseURL + "?city=" + city
				+ "&callback=flightHandler&callback=flightHandler&" + "_="
				+ time + "";
		String tag = "URL";
		// 生成请求对象
		Log.d(tag, url);
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		String strResult = "";

		// 发送请求
		try
		{

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity responseEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				Header header = response.getFirstHeader("Content-Encoding");
				if (header != null && header.getValue().equals("gzip"))
				{

					// 如果数据进行过压缩，先进行解压
					byte[] resultstream = EntityUtils
							.toByteArray(responseEntity);
					resultstream = GzipUtils.unGZip(resultstream);
					strResult = new String(resultstream, "UTF-8");
				} else
				{
					// 没进行过压缩，直接使用
					strResult = EntityUtils.toString(responseEntity);
				}
				// base64解码
				// strResult = new String(Base64.decode(strResult), "UTF-8");

				sb.append(strResult);
				String stTempString = sb.toString().substring(14,
						sb.toString().length());
				stTempString = stTempString.substring(0,
						stTempString.length() - 4);
				sb = new StringBuffer();
				sb.append(stTempString);
			}
			return sb.toString();

			// 显示响应

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getRequest(String url2)
	{

		StringBuffer sb = new StringBuffer();
		String baseURL = "http://wthrcdn.etouch.cn/weather_mini";
		String city = "%E4%B8%8A%E6%B5%B7";

		// 使用GET方法发送请求,需要把参数加在URL后面，用？连接，参数之间用&分隔
		String url = baseURL + "?city=" + city
				+ "&callback=flightHandler&callback=flightHandler&" + "_="
				+ System.currentTimeMillis() / 10000L + "";
		String tag = "URL";
		// 生成请求对象
		Log.d(tag, url);
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		// 发送请求
		try
		{

			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				Header header = response.getFirstHeader("Content-Encoding");
				if (header != null && header.getValue().equals("gzip"))
				{
					InputStream is = (response.getEntity().getContent());
					GZIPInputStream gzin = new GZIPInputStream(is);
					InputStreamReader isr = new InputStreamReader(gzin);
					java.io.BufferedReader br = new java.io.BufferedReader(isr);
					String tempbf;
					while ((tempbf = br.readLine()) != null)
					{
						sb.append(tempbf);
						sb.append("\r\n");
					}
					isr.close();
					gzin.close();
				}
				String stTempString = sb.toString().substring(14,
						sb.toString().length());
				stTempString = stTempString.substring(0,
						stTempString.length() - 4);
				sb = new StringBuffer();
				sb.append(stTempString);
			}
			return sb.toString();

			// 显示响应

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * post请求
	 * 
	 * @param urlString
	 * @param params
	 * @return
	 */
	public static String postRequest(String urlString,
			List<BasicNameValuePair> params)
	{

		try
		{
			// 1. 创建HttpClient对象
			HttpClient client = new DefaultHttpClient();
			// 2. 发get请求创建HttpGet对象
			HttpPost postMethod = new HttpPost(urlString);
			postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = client.execute(postMethod);
			int statueCode = response.getStatusLine().getStatusCode();
			if (statueCode == 200)
			{
				System.out.println(statueCode);
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e)
		{

		}

		return null;
	}

	@SuppressWarnings("unused")
	private void prpGetUrlPars(String urlString, Map<String, String> params)
	{
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(urlString);
		if (null != params)
		{

			urlBuilder.append("?");

			Iterator<Entry<String, String>> iterator = params.entrySet()
					.iterator();

			while (iterator.hasNext())
			{
				Entry<String, String> param = iterator.next();
				try
				{
					urlBuilder
							.append(URLEncoder.encode(param.getKey(), "UTF-8"))
							.append('=')
							.append(URLEncoder.encode(param.getValue(), "UTF-8"));
				} catch (UnsupportedEncodingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (iterator.hasNext())
				{
					urlBuilder.append('&');
				}
			}
		}
	}
}
