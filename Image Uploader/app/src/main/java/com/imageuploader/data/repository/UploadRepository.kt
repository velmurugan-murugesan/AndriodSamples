package com.imageuploader.data.repository

import com.imageuploader.base.BaseSubscriber
import com.imageuploader.data.model.LoginResponse
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.service.AuthApiService
import com.imageuploader.data.service.BasicAuthService
import io.reactivex.Single


class UploadRepository() : BaseSubscriber() {

    private val apiService = AuthApiService.createTicketService()

    companion object {
        fun getInstance() = UploadRepository()
    }

    fun doLogin(username: String, password: String) : Single<LoginResponse> {
        AppConstants.CLIENT_USERNAME = username
        AppConstants.CLIENT_PASSWORD = password
        return apiService.getLogin()
    }



}