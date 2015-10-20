package com.library.bean.EntityZh;

import java.io.Serializable;

public class Author implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2884872382350507364L;

	private String bio;

	private String hash;

	private String description;

	private String profileUrl;

	private Avatar avatar;

	private String slug;

	private String name;

	public void setBio(String bio)
	{
		this.bio = bio;
	}

	public String getBio()
	{
		return this.bio;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getHash()
	{
		return this.hash;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setProfileUrl(String profileUrl)
	{
		this.profileUrl = profileUrl;
	}

	public String getProfileUrl()
	{
		return this.profileUrl;
	}

	public void setAvatar(Avatar avatar)
	{
		this.avatar = avatar;
	}

	public Avatar getAvatar()
	{
		return this.avatar;
	}

	public void setSlug(String slug)
	{
		this.slug = slug;
	}

	public String getSlug()
	{
		return this.slug;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

}
