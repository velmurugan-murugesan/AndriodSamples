package com.example.kotlinfunctionsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val e = getValues()


    }

    fun getValues() : (Int, Int) -> Unit {

       return ::printSum
    }



    fun printSum(a: Int, b: Int) {
        println( a.plus(b))
    }


}