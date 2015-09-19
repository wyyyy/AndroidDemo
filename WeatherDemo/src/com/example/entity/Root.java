/**   
 * @Title: Root.java 
 * @Description: TODO
 * @author    
 * @date 2015-9-14 ����11:46:26 
 * @version V1.0   
 */

package com.example.entity;

import java.io.Serializable;

/**
 * @author john
 * @createtime 2015-9-14 john
 */
public class Root implements Serializable
{
	private String desc;

	private String status;

	private Data data;

	public Root()
	{
		super();
	}

	public Root(String desc, String status, Data dataList)
	{
		super();
		this.desc = desc;
		this.status = status;
		this.data = dataList;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getDesc()
	{
		return this.desc;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setData(Data dt)
	{
		this.data = dt;
	}

	public Data getData()
	{
		return this.data;
	}
}
