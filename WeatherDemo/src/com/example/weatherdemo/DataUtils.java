/**   
 * @Title: DataUtils.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 ����9:36:02 
 * @version V1.0   
 */

package com.example.weatherdemo;

/**
 * @author john
 * @createtime 2015-9-14 john
 */
public class DataUtils
{
	// / <summary>
	// / TIME
	// / </summary>
	// / <returns></returns>
	public static long GetSystemCurrentTimeMillis()
	{
		return (System.currentTimeMillis() - 621355968000000000L) / 10000L;
	}
}
