package com.omagrahari.weatherapptrell;

import android.app.Application;

import com.omagrahari.weatherapptrell.di.ActivityComponent;
import com.omagrahari.weatherapptrell.di.DaggerActivityComponent;
import com.omagrahari.weatherapptrell.di.module.ApplicationModule;

/**
 * Created by omprakash on 16,December,2019
 */
public class WeatherApplication extends Application {
    ActivityComponent activityComponent;
    public static WeatherApplication weatherApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        weatherApplication = this;

        activityComponent = DaggerActivityComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static WeatherApplication getWeatherApplication() {
        return weatherApplication;
    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }
}
