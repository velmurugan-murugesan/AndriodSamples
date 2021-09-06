package com.imageuploader.base

import com.google.gson.Gson
import com.imageuploader.data.model.ErrorType
import com.imageuploader.data.model.LoginErrorResponse
import com.imageuploader.features.login.LoginView
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

open class ErrorHandlerPresenter<out V:ErrorHandlerView>(view: V) : BasePresenter<V>(view) {

    fun onErrorHandler(throwable: Throwable) {

        var errorMessage = "Unknown Error"

        when(throwable) {

            is HttpException -> {

                val code = throwable.response().code()
                try {

                    if(code == 403) {
                        (getView() as LoginView).showNoAccess()
                    }

                    errorMessage = ErrorType.getMessage(code)
                } catch (e : Exception) {

                }
               /* val gson = Gson()
                val message = gson.fromJson(throwable.response().errorBody()?.charStream(), LoginErrorResponse::class.java)
                getView()?.showMessage(message.error_description)
            */}

            is SocketTimeoutException -> {
                errorMessage = "REQUEST TIMEOUT"
            }
        }

        getView().showMessage(errorMessage)
    }

}