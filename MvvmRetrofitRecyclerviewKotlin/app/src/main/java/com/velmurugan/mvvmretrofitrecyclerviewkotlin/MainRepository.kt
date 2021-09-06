package com.velmurugan.mvvmretrofitrecyclerviewkotlin

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()

}