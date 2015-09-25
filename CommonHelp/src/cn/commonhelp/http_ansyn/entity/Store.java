package cn.commonhelp.http_ansyn.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * 
 * ��������ʵ����
 * 
 * @author Administrator
 * 
 */
public class Store implements Serializable
{

	private static final long serialVersionUID = -4553916875364006709L;
	/*
	 * { "address":"�����h����l���d�˴�弞�2-4̖", "category":{
	 * "categoryName":"ʳ","id":1 }, "city":{ "cityName":"�����h","id":8 },
	 * "cityArea":{ "areaName":"����l","id":114 },
	 * "content":"�f���f���91��12��21����ʽ�I�\���@���O���u�����ľ��r��",
	 * "contentImageUrl":"\/images\/store\/content-8.jpg", "distance":0,
	 * "email":"hanlai@webusurf.com.cn", "id":8, "isVipStore":true,
	 * "latitude":31.235583, "longitude":121.417524,
	 * "name":"�����h����r���r�����e���f",
	 * "preferential":"��ƷM5000Ԫ�������Ͳ�ݮ����2�루��ݮ��6.5%���ҺУ���ֵ250Ԫ��",
	 * "synopsis":"�����M�˳������s�ط��������Ǵ���؅^�r��؟�o���J���΄ա���",
	 * "telphone":"037-994986", "titleImageUrl":"\/images\/store\/title-8.jpg",
	 * "updateTime":"2012-07-23", "webUrl":"http:\/\/www.dahufarm.org.tw\/" }
	 */

	/*
	 * "cardTypeList":[{"id":1,"typeName":"VISA"},{"id":2,"typeName":"MASTERCARD"
	 * }
	 * ,{"id":3,"typeName":"JCB"},{"id":4,"typeName":"���ڿ�"},{"id":5,"typeName"
	 * :"�y��"}], "storeNum":"", "typeSet":[{"id":1,"typeName":"���ڿ�"}], },
	 */

	/** Map key : id,typeName ; "cardTypeList":[{"id":1,"typeName":"VISA"}, */
	public List<Map<String, String>> cardTypeList;
	public String storeNum;
	/** "typeSet":[{"id":1,"typeName":"���ڿ�"}], */
	public List<Map<String, String>> typeSet;

	public String address;
	public Map<String, String> category;
	public Map<String, String> city;
	public Map<String, String> cityArea;
	public String content;
	public String contentImageUrl;
	public Integer distance;
	public String email;
	public Integer id;
	/** �ػ��̵� */
	public Boolean isVipStore;
	public Double latitude;
	public Double longitude;
	public String name;
	public String preferential;
	public String synopsis;
	public String telphone;
	public String titleImageUrl;
	public String updateTime;
	public String webUrl;

	/**
	 * ����json����
	 * 
	 * @param context
	 * @param data
	 *            json�ַ���
	 * @param isCache
	 *            �Ƿ񻺴浽���ݿ�
	 * @return ����ʱ�䣺2012-9-21 ����2:25:06
	 */
	public List<Store> getStoreList(Context context, String data,
			boolean isCache)
	{
		List<Store> list = new ArrayList<Store>();
		String tempItem = null;
		boolean isAdd = false;
		try
		{
			JSONArray jsonArray = new JSONArray(data);
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Store store = new Store();
				for (int j = 0; j < fields.length; j++)
				{
					Field field = fields[j];
					try
					{
						tempItem = fields[j].getName();
						try
						{
							jsonObject.get(tempItem);
						} catch (Exception e)
						{
							continue;
						}
						if (tempItem.equals("serialVersionUID")
								|| jsonObject.get(tempItem) == null
								|| jsonObject.get(tempItem).toString()
										.equals("null")
								|| jsonObject.get(tempItem).toString().trim()
										.length() == 0)
							continue;

						if (tempItem.equals("category")
								|| tempItem.equals("city")
								|| tempItem.equals("cityArea"))
						{
							Map<String, String> mmp = new HashMap<String, String>();
							JSONObject itemObj = jsonObject
									.getJSONObject(tempItem);
							Iterator<?> keys = itemObj.keys();
							while (keys.hasNext())
							{
								String key = keys.next().toString();
								// Log.i("store", key+ ":" +
								// itemObj.getString(key));
								mmp.put(key, itemObj.getString(key));
							}
							field.set(store, mmp);
							continue;
						}
						if (tempItem.equals("cardTypeList")
								|| tempItem.equals("typeSet"))
						{ // ���� cardTypeList typeSet
							List<Map<String, String>> listItem = new ArrayList<Map<String, String>>();
							JSONArray jarray = jsonObject
									.getJSONArray(tempItem);
							for (int k = 0; k < jarray.length(); k++)
							{
								Map<String, String> mmp = new HashMap<String, String>();
								JSONObject itemObj = jarray.getJSONObject(k);
								Iterator<?> keys = itemObj.keys();
								while (keys.hasNext())
								{
									String key = keys.next().toString();
									mmp.put(key, itemObj.getString(key));
								}
								listItem.add(mmp);
							}
							field.set(store, listItem);
							continue;
						}

						field.set(store, jsonObject.get(tempItem));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				if (isCache)
				{
					isAdd = false;
					try
					{
						// isAdd = new DBHelper(context).addStore(store);
					} catch (Exception e)
					{
					}
					if (isAdd)
					{ // ����������������
						for (int j = 0; j < fields.length; j++)
						{
							Field field = fields[j];
							try
							{
								tempItem = fields[j].getName();
								if (tempItem.equals("serialVersionUID")
										|| tempItem.equals("titleImageUrl")
										|| tempItem.equals("category")
										|| tempItem.equals("synopsis")
										|| tempItem.equals("name")
										|| tempItem.equals("id"))
									continue;
								field.set(store, null);
							} catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				list.add(store);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String toString()
	{
		return "Store [address=" + address + ", category=" + category
				+ ", city=" + city + ", cityArea=" + cityArea + ", content="
				+ content + ", contentImageUrl=" + contentImageUrl
				+ ", distance=" + distance + ", email=" + email + ", id=" + id
				+ ", isVipStore=" + isVipStore + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", name=" + name
				+ ", preferential=" + preferential + ", synopsis=" + synopsis
				+ ", telphone=" + telphone + ", titleImageUrl=" + titleImageUrl
				+ ", updateTime=" + updateTime + ", webUrl=" + webUrl + "]";
	}

}
