package com.imageuploader.features.login

import com.imageuploader.R
import com.imageuploader.base.*
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.data.model.LoginResponse
import com.imageuploader.data.repository.UploadRepository
import com.imageuploader.utils.Utils
import io.reactivex.observers.DisposableSingleObserver

class LoginPresenter(view: LoginView, val uploadRepository: UploadRepository) :
    ErrorHandlerPresenter<LoginView>(view) {

    fun validateLogin(username: String, password: String): Boolean {
        var status = false
        if (Utils.isValidEmail(username)) {
            if (Utils.isValidPassword(password)) {
                status = true
            } else {
                getView()?.showMessage(MyApp.instance.resources.getString(R.string.error_invalid_password))
            }
        } else {
            getView()?.showMessage(MyApp.instance.resources.getString(R.string.error_invalid_username))
        }
        return status
    }

    fun doLogin(username: String, password: String) {
        subscribe(
            uploadRepository.doLogin(username, password),
            object : DisposableSingleObserver<LoginResponse>() {
                override fun onSuccess(loginResponse: LoginResponse?) {
                    loginResponse?.let {

                        if(it.loggedUserRole != null) {
                            AppPreference.saveData(AppConstants.KEY_CREATE_TICKET, it.createTicketConfigFlag)
                            AppPreference.saveData(AppConstants.CLIENT_USERNAME, username)
                            AppPreference.saveData(AppConstants.CLIENT_PASSWORD, password)
                            getView().loginSuccess()
                        } else {
                            getView().showMessage("You do not have permission to access this site or page. Please contact the System Administrator if you wish to have access to this area.")
                        }


                    } ?: kotlin.run {
                        getView().showMessage("Login failed")
                    }
                }

                override fun onError(e: Throwable?) {
                    e?.let {
                        onErrorHandler(it)
                    }
                }
            })
    }
}

interface LoginView : BaseView, ErrorHandlerView {
    fun loginSuccess()
    fun showNoAccess()
}