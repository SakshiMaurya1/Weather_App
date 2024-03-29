package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("data/2.5/forecast?")
    Call<WeatherAPIResponse> getWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);
}
