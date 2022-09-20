package com.example.weatherapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("dt")
	public long dt;

	@SerializedName("pop")
	public double pop;

	@SerializedName("visibility")
	public float visibility;

	@SerializedName("dt_txt")
	public String dtTxt;

	@SerializedName("weather")
	public List<WeatherItem> weather;

	@SerializedName("main")
	public Main main;

	@SerializedName("clouds")
	public Clouds clouds;

	@SerializedName("sys")
	public Sys sys;

	@SerializedName("wind")
	public Wind wind;

	@SerializedName("rain")
	public Rain rain;

	public void setDt(int dt){
		this.dt = dt;
	}

	public long getDt(){
		return dt;
	}

	public void setPop(double pop){
		this.pop = pop;
	}

	public double getPop(){
		return pop;
	}

	public void setVisibility(int visibility){
		this.visibility = visibility;
	}

	public float getVisibility(){
		return visibility;
	}

	public void setDtTxt(String dtTxt){
		this.dtTxt = dtTxt;
	}

	public String getDtTxt(){
		return dtTxt;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setClouds(Clouds clouds){
		this.clouds = clouds;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	public void setWind(Wind wind){
		this.wind = wind;
	}

	public Wind getWind(){
		return wind;
	}

	public void setRain(Rain rain){
		this.rain = rain;
	}

	public Rain getRain(){
		return rain;
	}
}