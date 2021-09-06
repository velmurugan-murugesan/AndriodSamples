package com.example.cleanarchitectureandroid.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitectureandroid.utils.showMessage
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = MutableLiveData<Boolean>()

    fun onLogin(view: View) {
        when {
            username.value.isNullOrEmpty() -> {
                view.showMessage("Invalid username")
            }
            password.value.isNullOrEmpty() -> {
                view.showMessage("Invalid password")
            }
            else -> {
                loginSuccess.value = true
            }
        }

    }

}