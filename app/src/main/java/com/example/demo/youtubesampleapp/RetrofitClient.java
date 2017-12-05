package com.example.demo.youtubesampleapp;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitClient {

    static Retrofit getClient() {

        return new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
