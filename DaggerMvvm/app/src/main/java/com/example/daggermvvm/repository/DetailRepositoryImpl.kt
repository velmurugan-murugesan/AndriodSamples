package com.example.daggermvvm.repository

import com.example.daggermvvm.data.api.ApiClient
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor( val apiClient: ApiClient) : DetailRepository {
    override fun getMovieDetails() = apiClient.getDetails()
}