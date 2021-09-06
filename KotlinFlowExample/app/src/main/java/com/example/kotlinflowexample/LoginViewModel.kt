package com.example.kotlinflowexample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel @ViewModelInject constructor() : ViewModel() {

    var _username = MutableStateFlow("")
    var _password = MutableStateFlow("")
    var _email = MutableStateFlow("")

    fun setUsername(username: String) {
        this._username.value = username
    }

    fun setPassword(password: String) {
        this._password.value = password
    }

    fun setEmail(email: String) {
        this._email.value = email
    }



    val isLoginValid :Flow<Boolean> = combine(_username, _password,_email) {
        username, password, email ->
        val isUsernameValid = username.length > 4
        val isPasswordValid = password.length > 4
        val isEmailValid = email.length > 10
        return@combine isUsernameValid && isPasswordValid && isEmailValid
    }


}