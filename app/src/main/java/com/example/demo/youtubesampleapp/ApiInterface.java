package com.example.demo.youtubesampleapp;

import java.util.Map;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by admin on 05-Jul-17.
 */

public interface ApiInterface {


    @GET("videos") // getting youtube video details
    Call<VideoDetailsPojo> getYouTubeDetail(@QueryMap Map<String, String> Options);


}
