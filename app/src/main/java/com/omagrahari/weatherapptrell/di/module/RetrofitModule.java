package com.omagrahari.weatherapptrell.di.module;

import com.omagrahari.weatherapptrell.network.ApiReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.omagrahari.weatherapptrell.util.Constants.BASE_URL;

/**
 * Created by omprakash on 16,December,2019
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public ApiReference providesApiReference(Retrofit retrofit) {
        return retrofit.create(ApiReference.class);
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
