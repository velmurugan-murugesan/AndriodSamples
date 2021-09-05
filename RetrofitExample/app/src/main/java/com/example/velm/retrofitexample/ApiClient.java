package com.example.velm.retrofitexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by velmmuru on 8/19/2017.
 */

class ApiClient {


    private static final String BASE_URL ="http://10.0.2.2:3000/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){


        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }



}
