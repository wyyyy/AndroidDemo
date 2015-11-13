package com.library.dao;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.bean.XiaoHua.XHData;
import com.library.bean.XiaoHua.XiaohuaRoot;

public class ServiceXiaohua
{
  public static ArrayList<XHData> ReadJsonArr(String jsonString)
  {
    XiaohuaRoot roottValue = null;
    ArrayList<XHData> rtValue = null;
    try
    {
      if (jsonString.isEmpty())
      {
        return null;
      }
      roottValue = new Gson().fromJson(jsonString, new TypeToken<XiaohuaRoot>()
      {
      }.getType());

      long startTime = System.currentTimeMillis();

      long endTime = System.currentTimeMillis();
      System.out.println("time;" + (endTime - startTime) + "ms");
      rtValue = (ArrayList<XHData>) roottValue.getResult().getData();
      return rtValue;
    } catch (Exception e)
    {
      System.err.println("" + e.toString());
    }
    return rtValue;

  }
}
