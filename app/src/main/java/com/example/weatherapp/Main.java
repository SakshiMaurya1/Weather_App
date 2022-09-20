package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	public double temp;

	@SerializedName("temp_min")
	public double tempMin;

	@SerializedName("grnd_level")
	public double grndLevel;

	@SerializedName("temp_kf")
	public double tempKf;

	@SerializedName("humidity")
	public double humidity;

	@SerializedName("pressure")
	public double pressure;

	@SerializedName("sea_level")
	public double seaLevel;

	@SerializedName("feels_like")
	public double feelsLike;

	@SerializedName("temp_max")
	public double tempMax;

	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	public void setTempMin(double tempMin){
		this.tempMin = tempMin;
	}

	public double getTempMin(){
		return tempMin;
	}

	public void setGrndLevel(double grndLevel){
		this.grndLevel = grndLevel;
	}

	public double getGrndLevel(){
		return grndLevel;
	}

	public void setTempKf(double tempKf){
		this.tempKf = tempKf;
	}

	public double getTempKf(){
		return tempKf;
	}

	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
		return humidity;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setSeaLevel(double seaLevel){
		this.seaLevel = seaLevel;
	}

	public double getSeaLevel(){
		return seaLevel;
	}

	public void setFeelsLike(double feelsLike){
		this.feelsLike = feelsLike;
	}

	public double getFeelsLike(){
		return feelsLike;
	}

	public void setTempMax(double tempMax){
		this.tempMax = tempMax;
	}

	public double getTempMax(){
		return tempMax;
	}
}