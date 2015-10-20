package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.bean.EntityZh.RootZhihu;

public class ServiceZhihu
{

	public static List<RootZhihu> getList(String url)
	{

		return null;// ReadJsonArr(AsyncHttpClientHelp.SendGet(url));

	}

	public static ArrayList<RootZhihu> ReadJsonArr(String jsonString)
	{
		ArrayList<RootZhihu> listCatalogs = null;
		try
		{
			if (jsonString.isEmpty())
			{
				return null;
			}
			listCatalogs = new Gson().fromJson(jsonString, new TypeToken<List<RootZhihu>>()
			{
			}.getType());

			String tipString = "";
			long startTime = System.currentTimeMillis();//
			ArrayList<String> urList = new ArrayList<String>();
			for (int i = 0; i < listCatalogs.size(); i++)
			{
				tipString = listCatalogs.get(i).getTitle();
				System.out.println(i + ":" + tipString);
				tipString = listCatalogs.get(i).getTitleImage();
				urList.add(listCatalogs.get(i).getTitleImage());
				System.out.println(i + ":" + "urL：" + tipString);
				// System.out.println("" + tipString);
				String sFileName = tipString.substring(tipString.lastIndexOf("/") + 1);
				// HttpDownImg.DownImg(tipString,sFileName);
				// downimg(tipString);

			}

			long endTime = System.currentTimeMillis();
			System.out.println("time;" + (endTime - startTime) + "ms");
			return listCatalogs;
		} catch (Exception e)
		{
			System.err.println("" + e.toString());
		}
		return listCatalogs;

	}

}
