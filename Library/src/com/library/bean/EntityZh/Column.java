package com.library.bean.EntityZh;

import java.io.Serializable;

public class Column implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8879010725110070416L;

	private String slug;

	private String name;

	public void setSlug(String slug){
	this.slug = slug;
	}
	public String getSlug(){
	return this.slug;
	}
	public void setName(String name){
	this.name = name;
	}
	public String getName(){
	return this.name;
	}

}
