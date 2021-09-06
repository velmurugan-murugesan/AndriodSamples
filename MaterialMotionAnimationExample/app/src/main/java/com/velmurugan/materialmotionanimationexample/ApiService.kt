package com.velmurugan.materialmotionanimationexample

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllMovies() : List<Movie>

    companion object {

        var apiService: ApiService? = null

        fun getInstance() : ApiService {


            if (apiService == null) {
                val client = OkHttpClient.Builder()
                    //.addInterceptor(MockInterceptor())
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }

    }


}