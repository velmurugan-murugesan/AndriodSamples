package com.velmurugan.kotlincoroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    /*
        1. Suspend keyword
        2. Scope - Global scope, Life Cycle Scope, View Model Scope
        3. Launch a coroutines - Launch / Async
        4. withContext
        5. Dispatchers - Main Dispatchers, IO Dispatchers, Default Dispatchers, Unconfined Dispatchers

     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        GlobalScope.launch {

            val a1 = async {
                println("A1 - start")
                delay(6000L)
                println("A1 - complete")
                "1"
            }

            val a2 = async {
                println("A2 - start")
                delay(4000L)
                println("A2 - complete")
                "2"
            }

            val a = a1.await()  + a2.await()
            println(a)
            println("completed")
        }

        GlobalScope.launch {

            val a1 = async {
                println("A3 - start")
                delay(8000L)
                println("A3 - complete")
                "1"
            }

            val a2 = async {
                println("A4 - start")
                delay(2000L)
                println("A4 - complete")
                "2"
            }

            val a = a1.await()  + a2.await()
            println(a)
            println("completed")
        }
    }


}