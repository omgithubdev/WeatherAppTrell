package com.omagrahari.weatherapptrell.entity;

/**
 * Created by omprakash on 16,December,2019
 */
public class Weather {
    String temp;
    String tempCelcius;
    String humidity;

    public String getTempCelcius() {
        return tempCelcius + "\u2103";
    }

    public void setTempCelcius(String tempCelcius) {
        this.tempCelcius = tempCelcius;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
