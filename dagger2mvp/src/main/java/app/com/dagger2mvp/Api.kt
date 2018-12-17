package app.com.dagger2mvp

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("5c0535dd3300005f00e81176")
    fun getAllUsers() : Call<List<User>>

}