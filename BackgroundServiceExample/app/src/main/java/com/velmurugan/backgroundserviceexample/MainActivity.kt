package com.velmurugan.backgroundserviceexample

import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {

            val url = "http://192.168.225.1"
            try {
                //Connect to the website
                val document: Document = Jsoup.connect(url).get()
                val change = document.body().getElementById("batterylevel")
                val batteryStatus = document.body().getElementById("batterystatus")
                Log.d("Charge", "onCreate: ${change.`val`()} ")
                Log.d("batteryStatus", "onCreate: ${batteryStatus.`val`()} ")
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}