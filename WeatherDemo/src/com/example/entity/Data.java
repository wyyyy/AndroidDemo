package com.example.entity;

public class Data
{
	public Data()
	{
		super();
	}

	private String wendu;

	private String ganmao;

	private Forecast[] forecast;

	private Yesterday yesterday;

	private String aqi;

	private String city;

	public void setWendu(String wendu)
	{
		this.wendu = wendu;
	}

	public String getWendu()
	{
		return this.wendu;
	}

	public void setGanmao(String ganmao)
	{
		this.ganmao = ganmao;
	}

	public String getGanmao()
	{
		return this.ganmao;
	}

	public void setForecast(Forecast[] forecast)
	{
		this.forecast = forecast;
	}

	public Forecast[] getForecast()
	{
		return this.forecast;
	}

	public void setYesterday(Yesterday yesterday)
	{
		this.yesterday = yesterday;
	}

	public Yesterday getYesterday()
	{
		return this.yesterday;
	}

	public void setAqi(String aqi)
	{
		this.aqi = aqi;
	}

	public String getAqi()
	{
		return this.aqi;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCity()
	{
		return this.city;
	}

}
