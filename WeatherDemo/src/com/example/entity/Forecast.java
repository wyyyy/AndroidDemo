/**   
 * @Title: forecast.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 ����11:45:52 
 * @version V1.0   
 */

package com.example.entity;

import java.io.Serializable;

/**
 * @author john
 * @createtime 2015-9-14 john
 */
public class Forecast implements Serializable
{
	private String fengxiang;

	private String fengli;

	private String high;

	private String type;

	private String low;

	private String date;

	public void setFengxiang(String fengxiang)
	{
		this.fengxiang = fengxiang;
	}

	public String getFengxiang()
	{
		return this.fengxiang;
	}

	public void setFengli(String fengli)
	{
		this.fengli = fengli;
	}

	public String getFengli()
	{
		return this.fengli;
	}

	public void setHigh(String high)
	{
		this.high = high;
	}

	public String getHigh()
	{
		return this.high;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return this.type;
	}

	public void setLow(String low)
	{
		this.low = low;
	}

	public String getLow()
	{
		return this.low;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDate()
	{
		return this.date;
	}
}
