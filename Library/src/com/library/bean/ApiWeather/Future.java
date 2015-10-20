package com.library.bean.ApiWeather;

public class Future
{
	private String temperature;

	private String weather;

	private String weather_id;

	private String wind;

	private String week;

	private String date;

	public void setTemperature(String temperature){
	this.temperature = temperature;
	}
	public String getTemperature(){
	return this.temperature;
	}
	public void setWeather(String weather){
	this.weather = weather;
	}
	public String getWeather(){
	return this.weather;
	}
	public void setWeather_id(String weather_id){
	this.weather_id = weather_id;
	}
	public String getWeather_id(){
	return this.weather_id;
	}
	public void setWind(String wind){
	this.wind = wind;
	}
	public String getWind(){
	return this.wind;
	}
	public void setWeek(String week){
	this.week = week;
	}
	public String getWeek(){
	return this.week;
	}
	public void setDate(String date){
	this.date = date;
	}
	public String getDate(){
	return this.date;
	}
}
