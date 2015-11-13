package com.news.dao;

import java.util.List;
import java.util.Map;

import com.news.bean.News.ChannelItem;

import android.content.ContentValues;
/**
 * cache manage
 * @author john
 *
 */
public interface IChannelDao
{

  public boolean addCache(ChannelItem item);

  public boolean deleteCache(String whereClause, String[] whereArgs);

  public boolean updateCache(ContentValues values, String whereClause, String[] whereArgs);

  public Map<String, String> viewCache(String selection, String[] selectionArgs);

  public List<Map<String, String>> listCache(String selection, String[] selectionArgs);

  public void clearFeedTable();
}
