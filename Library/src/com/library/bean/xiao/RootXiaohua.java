package com.library.bean.xiao;

import java.io.Serializable;

public class RootXiaohua implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3340723315106552432L;

	private int error_code;

	private String reason;

	private XHResult result;

	public void setError_code(int error_code)
	{
		this.error_code = error_code;
	}

	public int getError_code()
	{
		return this.error_code;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getReason()
	{
		return this.reason;
	}

	public void setResult(XHResult result)
	{
		this.result = result;
	}

	public XHResult getResult()
	{
		return this.result;
	}
}
