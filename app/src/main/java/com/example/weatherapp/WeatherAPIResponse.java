package com.example.weatherapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WeatherAPIResponse{

	@SerializedName("city")
	private City city;

	@SerializedName("cnt")
	private float cnt;

	@SerializedName("cod")
	private String cod;

	@SerializedName("message")
	private float message;

	@SerializedName("list")
	public List<ListItem> list;

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setCnt(int cnt){
		this.cnt = cnt;
	}

	public float getCnt(){
		return cnt;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getCod(){
		return cod;
	}

	public void setMessage(int message){
		this.message = message;
	}

	public float getMessage(){
		return message;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}
}