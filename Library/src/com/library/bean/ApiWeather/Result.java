package com.library.bean.ApiWeather;

import java.util.List;

public class Result
{
	private Sk sk;

	private Today today;

	private List<Future> futures;

	public void setSk(Sk sk)
	{
		this.sk = sk;
	}

	public Sk getSk()
	{
		return this.sk;
	}

	public void setToday(Today today)
	{
		this.today = today;
	}

	public Today getToday()
	{
		return this.today;
	}

	public void setFuture(List<Future> future)
	{
		this.futures = future;
	}

	public List<Future> getFuture()
	{
		return this.futures;
	}
}
