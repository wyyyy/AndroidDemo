package com.library.bean.shfzhihu;

import java.util.ArrayList;

public class ShfRoot
{
	private String title;
	private String titleUrl;
	private String updatetiem;
	private ArrayList<ShfReply> _listRtAll;

	/**
	 * @return the titleUrl
	 */
	public String getTitleUrl()
	{
		return titleUrl;
	}

	/**
	 * @param titleUrl
	 *            the titleUrl to set
	 */
	public void setTitleUrl(String titleUrl)
	{
		this.titleUrl = titleUrl;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the updatetiem
	 */
	public String getUpdatetiem()
	{
		return updatetiem;
	}

	/**
	 * @param updatetiem
	 *            the updatetiem to set
	 */
	public void setUpdatetiem(String updatetiem)
	{
		this.updatetiem = updatetiem;
	}

	/**
	 * @return the _listRtAll
	 */
	public ArrayList<ShfReply> get_listRtAll()
	{
		return _listRtAll;
	}

	/**
	 * @param rtAll
	 *            the _listRtAll to set
	 */
	public void set_listRtAll(ArrayList<ShfReply> rtAll)
	{
		_listRtAll = rtAll;
	}

}
