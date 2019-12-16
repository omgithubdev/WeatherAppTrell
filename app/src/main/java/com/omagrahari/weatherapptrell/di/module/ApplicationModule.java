package com.omagrahari.weatherapptrell.di.module;

import android.app.Application;

import com.omagrahari.weatherapptrell.WeatherApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omprakash on 16,December,2019
 */
@Module
public class ApplicationModule {
    WeatherApplication weatherApplication;

    public ApplicationModule(WeatherApplication weatherApplication) {
        this.weatherApplication = weatherApplication;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return weatherApplication;
    }
}
