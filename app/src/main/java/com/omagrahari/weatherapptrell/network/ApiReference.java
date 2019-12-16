package com.omagrahari.weatherapptrell.network;

import com.omagrahari.weatherapptrell.entity.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by omprakash on 16,December,2019
 */
public interface ApiReference {

    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeatherData(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appid);
}
