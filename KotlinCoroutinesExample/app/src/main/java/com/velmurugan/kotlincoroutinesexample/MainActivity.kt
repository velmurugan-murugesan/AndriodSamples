package com.velmurugan.kotlincoroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
/*
    1. Suspend keyword
    2. Scope - Global scope, Life Cycle Scope, View Model Scope
    3. Launch a coroutines - Launch / Async
    4. withContext
    5. Dispatchers - Main Dispatchers, IO Dispatchers, Default Dispatchers, Unconfined Dispatchers

 */
class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"Before runblocking")
        runBlocking {
            delay(2000)
            Log.d(TAG,"Inside runblocking")
        }
        Log.d(TAG,"After runblocking")
    }
}