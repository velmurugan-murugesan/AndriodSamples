package com.subaru.instrumenttestingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        helloworld.setOnClickListener {
            clickListener()
        }
    }

    private fun clickListener() {
        println("Helllp")
    }
}