package com.library.bean.EntityZh;

import java.io.Serializable;
import java.util.List;

public class RootZhihu implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5518677861267068851L;

	private String rating;

	private String sourceUrl;

	private String publishedTime;

	private Links links;

	private Author author;

	private Column column;

	private List<Topics> topicss;

	private String title;

	private String titleImage;

	private String summary;

	private String content;

	private String url;

	private String state;

	private String href;

	private Meta meta;

	private String commentPermission;

	private String snapshotUrl;

	private boolean canComment;

	private int slug;

	private int commentsCount;

	private int likesCount;

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public String getRating()
	{
		return this.rating;
	}

	public void setSourceUrl(String sourceUrl)
	{
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUrl()
	{
		return this.sourceUrl;
	}

	public void setPublishedTime(String publishedTime)
	{
		this.publishedTime = publishedTime;
	}

	public String getPublishedTime()
	{
		return this.publishedTime;
	}

	public void setLinks(Links links)
	{
		this.links = links;
	}

	public Links getLinks()
	{
		return this.links;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public Author getAuthor()
	{
		return this.author;
	}

	public void setColumn(Column column)
	{
		this.column = column;
	}

	public Column getColumn()
	{
		return this.column;
	}

	public void setTopics(List<Topics> topics)
	{
		this.topicss = topics;
	}

	public List<Topics> getTopics()
	{
		return this.topicss;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitleImage(String titleImage)
	{
		this.titleImage = titleImage;
	}

	public String getTitleImage()
	{
		return this.titleImage;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getSummary()
	{
		return this.summary;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getState()
	{
		return this.state;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public String getHref()
	{
		return this.href;
	}

	public void setMeta(Meta meta)
	{
		this.meta = meta;
	}

	public Meta getMeta()
	{
		return this.meta;
	}

	public void setCommentPermission(String commentPermission)
	{
		this.commentPermission = commentPermission;
	}

	public String getCommentPermission()
	{
		return this.commentPermission;
	}

	public void setSnapshotUrl(String snapshotUrl)
	{
		this.snapshotUrl = snapshotUrl;
	}

	public String getSnapshotUrl()
	{
		return this.snapshotUrl;
	}

	public void setCanComment(boolean canComment)
	{
		this.canComment = canComment;
	}

	public boolean getCanComment()
	{
		return this.canComment;
	}

	public void setSlug(int slug)
	{
		this.slug = slug;
	}

	public int getSlug()
	{
		return this.slug;
	}

	public void setCommentsCount(int commentsCount)
	{
		this.commentsCount = commentsCount;
	}

	public int getCommentsCount()
	{
		return this.commentsCount;
	}

	public void setLikesCount(int likesCount)
	{
		this.likesCount = likesCount;
	}

	public int getLikesCount()
	{
		return this.likesCount;
	}

}