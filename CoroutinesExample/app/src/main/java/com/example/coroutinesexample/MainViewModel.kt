package com.example.coroutinesexample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val users = MutableLiveData<List<String>>()

    fun fetchUsers() {

        viewModelScope.launch {

            val users10 = async { get10Users() }
            val user5 = async { get5Users() }

            Log.d("TAG", "fetchUsers: Running")

            val userSet1 = user5.await()
            Log.d("TAG", "fetchUsers: Await 1")
            val userSet2 = users10.await()
            Log.d("TAG", "fetchUsers: Await 2")

            users.postValue(userSet1)

        }

    }



    suspend fun get10Users() : List<String> {
        val users = mutableListOf<String>()
        for (i in 1..10) {
            delay(1000)
            users.add("In 10 Users $i")
        }
        return users
    }

    suspend fun get5Users() : List<String> {
        val users = mutableListOf<String>()
        for (i in 1..5) {
            delay(1000)
            users.add("In 5 Users $i")
        }
        return users
    }


}