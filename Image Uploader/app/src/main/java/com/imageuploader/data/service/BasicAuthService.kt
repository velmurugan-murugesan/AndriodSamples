package com.imageuploader.data.service

import com.imageuploader.BuildConfig
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.model.LoginResponse
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface BasicAuthService {



    @FormUrlEncoded
    @POST("scrapsystem/api/oauth/token")
    fun doLogin(@Header("Authorization") authorization: String, @FieldMap loginRequest: Map<String, String>): Single<LoginResponse>


    companion object Factory {

        fun create(): BasicAuthService {

            val basicAuthInterceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic(AppConstants.CLIENT_USERNAME, AppConstants.CLIENT_PASSWORD)).build()
                chain.proceed(newRequest)
            }

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            var client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(basicAuthInterceptor)
                .build()

            if (BuildConfig.BUILD_TYPE.equals("staging",true)) {
                client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(MockInterceptor())
                    .addInterceptor(basicAuthInterceptor)
                    .build()
            }

            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(client).baseUrl(
                    BuildConfig.BASE_URL)
                .build()
            return retrofit.create(BasicAuthService::class.java)
        }

    }

}