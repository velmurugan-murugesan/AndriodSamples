package app.com.mvvmsample.view

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("movielist.json")
    fun movieList() : Single<List<Movie>>

    companion object {

        val BASE_URL = "http://velmm.com/apis/"

        fun create() : ApiInterface {

            var retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}