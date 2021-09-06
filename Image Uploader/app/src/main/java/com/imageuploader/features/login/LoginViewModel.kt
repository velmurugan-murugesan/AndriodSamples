package com.imageuploader.features.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imageuploader.R
import com.imageuploader.base.MyApp
import com.imageuploader.data.model.LoginResponse
import com.imageuploader.utils.Utils

class LoginViewModel : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    val login = MutableLiveData<Boolean>()
    var loginResponse = MutableLiveData<LoginResponse>()

/*

    fun doLogin(loginRequest: LoginRequest) {
        loginResponse.value?.let {} ?: kotlin.run {
            loginResponse.value = CampaignRepository.getInstance().doLogin(loginRequest).value
        }
    }
*/

   /* fun doLogin(): MutableLiveData<LoginResponse> {
        val loginRequest = LoginRequest(username.value!!, password.value!!, "password")
        return UploadRepository.getInstance().doLogin(loginRequest)
    }*/


    fun validateLogin() {
        if (Utils.isValidEmail(username.value)) {
            if (Utils.isValidPassword(password.value)) {
                login.value = true
            } else {
                errorMessage.value = MyApp.instance.resources.getString(R.string.error_invalid_password)
            }
        } else {
            errorMessage.value = MyApp.instance.resources.getString(R.string.error_invalid_username)
        }

    }

}