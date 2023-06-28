package com.example.weather;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    static final String UTILS_TAG = "UtilsTag";
    public static String buildURL(double latitude, double longitude){
        String p1 = "https://api.openweathermap.org/data/2.5/weather?lat=";
        String p2 = "&lon=";
        String p3 = "&appid=08d5524f0b8dc8560d8baabdd2c115d7";
        String p4 = Double.toString(latitude);
        String p5 = Double.toString(longitude);
        URL url = null;
        try{
            url = new URL(p1 + p4 + p2 + p5 +p3);

        }catch(MalformedURLException e) {
            Log.i(UTILS_TAG, "malformed URL: " + url.toString());
        }
        return url.toString();
    }
}