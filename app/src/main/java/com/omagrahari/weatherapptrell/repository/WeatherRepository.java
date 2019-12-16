package com.omagrahari.weatherapptrell.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.omagrahari.weatherapptrell.R;
import com.omagrahari.weatherapptrell.entity.WeatherResponse;
import com.omagrahari.weatherapptrell.network.ApiReference;
import com.omagrahari.weatherapptrell.util.Utils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.omagrahari.weatherapptrell.WeatherApplication.getWeatherApplication;

/**
 * Created by omprakash on 16,December,2019
 */
public class WeatherRepository {
    Application application;

    @Inject
    ApiReference apiReference;

    MutableLiveData<WeatherResponse> weatherResponseMutableLiveData = new MutableLiveData<>();

    public WeatherRepository(Application application) {
        this.application = application;

        getWeatherApplication().getComponent().inject(this);
    }

    public MutableLiveData<WeatherResponse> getWeather(double latitude, double longitude) {
        apiReference.getWeatherData(latitude, longitude, application.getString(R.string.appid)).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    weatherResponse.getMain().setTempCelcius(String.valueOf(Utils.convertKelvinToCelcius(Float.parseFloat(weatherResponse.getMain().getTemp()))));
                    weatherResponseMutableLiveData.postValue(weatherResponse);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("CHECKPOINT", "CHECK ERROR::" + t.getLocalizedMessage());
            }
        });

        return weatherResponseMutableLiveData;
    }

}
