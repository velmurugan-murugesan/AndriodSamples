package com.velmurugan.kotlincoroutinesexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getAllUsers() {
        viewModelScope.launch {

        }
    }

}