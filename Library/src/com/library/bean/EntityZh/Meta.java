package com.library.bean.EntityZh;

import java.io.Serializable;

public class Meta implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682638588643804613L;
	private String previous;
	private String next;

	public String getPrevious()
	{
		return previous;
	}

	public void setPrevious(String previous)
	{
		this.previous = previous;
	}

	public String getNext()
	{
		return next;
	}

	public void setNext(String next)
	{
		this.next = next;
	}

}
