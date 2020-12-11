package com.example.cleanarchitectureandroid.domain.usecases

import com.example.cleanarchitectureandroid.utils.ResultWrapper
import io.reactivex.Single
import retrofit2.Response

interface SingleUseCase<T> {

    suspend fun execute() : T
}