package com.example.demo.youtubesampleapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/28/2017.
 */

public class Config {
    public  static final  String YOUTUBE_KEY="AIzaSyDeiWqZ3r9TlHBsksh-bOOL6SukkBsCNXA";

    public  static final  String YOUTUBE_VIDEO_CODE = "_oEA18Y8gM0";
    public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/";


    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(YOUTUBE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
