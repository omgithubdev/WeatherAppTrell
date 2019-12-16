package com.omagrahari.weatherapptrell.util;

import java.text.DecimalFormat;

/**
 * Created by omprakash on 16,December,2019
 */
public class Utils {
    public static float convertKelvinToCelcius(float kelvin) {
        float celsius = kelvin - 273.15f;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Float.parseFloat(decimalFormat.format(celsius));
    }
}
