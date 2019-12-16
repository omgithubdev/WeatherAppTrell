package com.omagrahari.weatherapptrell.di;

import com.omagrahari.weatherapptrell.MainActivity;
import com.omagrahari.weatherapptrell.di.module.ApplicationModule;
import com.omagrahari.weatherapptrell.di.module.RepositoryModule;
import com.omagrahari.weatherapptrell.di.module.RetrofitModule;
import com.omagrahari.weatherapptrell.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by omprakash on 16,December,2019
 */
@Singleton
@Component(modules = {ApplicationModule.class, RetrofitModule.class, RepositoryModule.class})
public interface ActivityComponent {

    public void inject(MainActivity mainActivity);

    public void inject(WeatherRepository weatherRepository);
}
