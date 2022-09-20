package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class WeatherItem{

	@SerializedName("icon")
	public String icon;

	@SerializedName("description")
	public String description;

	@SerializedName("main")
	public String main;

	@SerializedName("id")
	public int id;

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMain(String main){
		this.main = main;
	}

	public String getMain(){
		return main;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}