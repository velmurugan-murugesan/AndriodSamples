package com.velmurugan.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            showText(name = "Velmurugan")
        }
    }

    @Composable
    fun showText(name: String) {
        Text(text = name)
    }

    @Preview
    @Composable
    fun test() {
        showText(name = "Velmurugan")
    }

}