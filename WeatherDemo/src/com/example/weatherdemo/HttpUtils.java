/**   
 * @Title: HttpUtils.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 ����9:47:42 
 * @version V1.0   
 */

package com.example.weatherdemo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import android.R.string;
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
	 * ����������״̬�����ж�
	 * 
	 * @return true, ���ã� false�� ������
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
		long age = DataUtils.GetSystemCurrentTimeMillis();

		// ʹ��GET������������,��Ҫ�Ѳ�������URL���棬�ã����ӣ�����֮����&�ָ�
		String url = baseURL + "?city=" + city
				+ "&callback=flightHandler&callback=flightHandler&" + "_="
				+ System.currentTimeMillis() / 10000L + "";
		String tag = "URL";
		// �����������
		Log.d(tag, url);
		// url="http://www.weather.com.cn/adat/cityinfo/101010100.html";
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		String strResult = "";

		// ��������
		try
		{

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity responseEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				Header header = response.getFirstHeader("Content-Encoding");
				if (header != null && header.getValue().equals("gzip"))
				{

					// ������ݽ��й�ѹ�����Ƚ��н�ѹ
					byte[] resultstream = EntityUtils
							.toByteArray(responseEntity);
					resultstream = GzipUtils.unGZip(resultstream);
					strResult = new String(resultstream, "UTF-8");
				} else
				{
					// û���й�ѹ����ֱ��ʹ��
					strResult = EntityUtils.toString(responseEntity);
					sb.append(strResult);
					return sb.toString();
				}
				// base64����
				// strResult = new String(Base64.decode(strResult), "UTF-8");

				sb.append(strResult);
				String stTempString = sb.toString().substring(14,
						sb.toString().length());
				stTempString = stTempString.substring(0,
						stTempString.length() - 2);
				sb = new StringBuffer();
				sb.append(stTempString);
			}
			return sb.toString();

			// ��ʾ��Ӧ

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
		long age = DataUtils.GetSystemCurrentTimeMillis();

		// ʹ��GET������������,��Ҫ�Ѳ�������URL���棬�ã����ӣ�����֮����&�ָ�
		String url = baseURL + "?city=" + city
				+ "&callback=flightHandler&callback=flightHandler&" + "_="
				+ System.currentTimeMillis() / 10000L + "";
		String tag = "URL";
		// �����������
		Log.d(tag, url);
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		// ��������
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

			// ��ʾ��Ӧ

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * post����
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
			// 1. ����HttpClient����
			HttpClient client = new DefaultHttpClient();
			// 2. ��get���󴴽�HttpGet����
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
