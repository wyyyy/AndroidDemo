package com.library.bean.ApiWeather;
/*
 * API Weather Entity
 */
public class ApiWeatherRoot
{

	private String resultcode;

	private String reason;

	private Result result;

	private int error_code;

	public void setResultcode(String resultcode)
	{
		this.resultcode = resultcode;
	}

	public String getResultcode()
	{
		return this.resultcode;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getReason()
	{
		return this.reason;
	}

	public void setResult(Result result)
	{
		this.result = result;
	}

	public Result getResult()
	{
		return this.result;
	}

	public void setError_code(int error_code)
	{
		this.error_code = error_code;
	}

	public int getError_code()
	{
		return this.error_code;
	}

}
