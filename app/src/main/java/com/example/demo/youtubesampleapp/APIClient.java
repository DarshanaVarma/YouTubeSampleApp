package com.example.demo.youtubesampleapp;

/**
 * Created by swanand on 3/15/2017.
 */

public class APIClient {

    public static ApiInterface getAPIClient(){

        return RetrofitClient.getClient().create(ApiInterface.class);
    }
}
