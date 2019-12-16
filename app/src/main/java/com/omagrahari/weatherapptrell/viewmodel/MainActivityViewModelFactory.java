package com.omagrahari.weatherapptrell.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.omagrahari.weatherapptrell.repository.WeatherRepository;

import javax.inject.Inject;

/**
 * Created by omprakash on 16,December,2019
 */
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    WeatherRepository weatherRepository;

    @Inject
    MainActivityViewModelFactory(WeatherRepository weatherRepository){
        this.weatherRepository = weatherRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(weatherRepository);
    }
}
