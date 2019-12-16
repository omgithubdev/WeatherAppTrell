package com.omagrahari.weatherapptrell;

import com.omagrahari.weatherapptrell.util.Utils;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by omprakash on 16,December,2019
 */
public class UnitTests {

    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = Utils.convertKelvinToCelcius(300.38f);
        // expected value is 212
        float expected = 28f;
        // use this method because float is not precise
        assertEquals("Conversion from kelvin to celcius failed", expected, actual, 0.001);
    }
}
