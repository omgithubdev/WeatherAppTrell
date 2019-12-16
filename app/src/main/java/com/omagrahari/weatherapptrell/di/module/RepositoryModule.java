package com.omagrahari.weatherapptrell.di.module;

import android.app.Application;

import com.omagrahari.weatherapptrell.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omprakash on 16,December,2019
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public WeatherRepository providesWeatherRespository(Application application) {
        return new WeatherRepository(application);
    }
}
