package com.example.weatherdemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.entity.*;
import com.example.entity.Forecast;

public class JsonTools {
	/**
	 * 获取对象数据 //采用android内置的org.json包解析
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Root getRoot(String jsonString) {
		Root root = new Root();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);

			String status = jsonObject.getString("status");
			String desc = jsonObject.getString("desc");
			JSONObject dataJSON = jsonObject.getJSONObject("data");
			String wendu = dataJSON.getString("wendu");
			String ganmao = dataJSON.getString("ganmao");
			String aqi = dataJSON.getString("aqi");
			String city = dataJSON.getString("city");
			root.setDesc(desc);
			root.setStatus(status);
			Data dt = new Data();
			dt.setWendu(wendu);
			dt.setGanmao(ganmao);
			dt.setAqi(aqi);
			dt.setCity(city);
			// 返回json的数组
			JSONArray jsonArray = dataJSON.getJSONArray("forecast");
			List<Forecast> forecast = new ArrayList<Forecast>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Forecast ft = new Forecast();
				ft.setFengxiang(jsonObject2.getString("fengxiang"));
				ft.setFengli(jsonObject2.getString("fengli"));
				ft.setHigh(jsonObject2.getString("high"));
				ft.setType(jsonObject2.getString("type"));
				ft.setLow(jsonObject2.getString("low"));
				ft.setDate(jsonObject2.getString("date"));

				forecast.add(ft);
			}

			//dt.setForecast(forecast);

			root.setData(dt);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return root;
	}
}
