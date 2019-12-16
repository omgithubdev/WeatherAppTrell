package com.omagrahari.weatherapptrell.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omagrahari.weatherapptrell.entity.WeatherResponse;
import com.omagrahari.weatherapptrell.repository.WeatherRepository;

/**
 * Created by omprakash on 16,December,2019
 */
public class MainActivityViewModel extends ViewModel {
    WeatherRepository weatherRepository;

    public MainActivityViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LiveData<WeatherResponse> getWeather(double latitude, double longitude) {
        return weatherRepository.getWeather(latitude, longitude);
    }

}
