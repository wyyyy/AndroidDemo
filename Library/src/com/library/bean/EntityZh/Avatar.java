package com.library.bean.EntityZh;

import java.io.Serializable;

public class Avatar implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 715965609820111593L;

	private String id;

	private String template;

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return this.id;
	}

	public void setTemplate(String template)
	{
		this.template = template;
	}

	public String getTemplate()
	{
		return this.template;
	}

}