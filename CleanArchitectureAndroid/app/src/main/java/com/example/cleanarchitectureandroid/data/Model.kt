package com.example.cleanarchitectureandroid.data

import com.google.gson.annotations.SerializedName
import javax.inject.Named

data class Movie(val title: String, val id: String, val image: String)

data class MovieDetail(val title: String, val image: String, val desc: String, val date: String, @SerializedName("Country")val country: String)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}