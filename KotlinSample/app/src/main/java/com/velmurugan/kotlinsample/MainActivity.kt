package com.velmurugan.kotlinsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        withInternetConnection { name ->

            returnFunction()
        }

    }
}

fun fun2(value: Int) {
    print(2)
}

fun returnFunction(): (value: Int) -> Unit {
    val a = 5 - 3
    return { fun2(a) }
}

inline fun withInternetConnection(fun1: (name: String) -> ((Int) -> Unit)) {
    if (isInternetConnected()) {
        fun1("Hello")
    }
}

fun isInternetConnected() = true