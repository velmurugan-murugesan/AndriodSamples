package com.velmurugan.jetpackcomposesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting("Hello")
            MultiRow()
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = name, style = Typography.)
    }

    @Composable
    fun MultiRow() {
        Column {
            Text(text = "Text 1")
            Text(text = "Text 2")
            Text(text = "Text 3")
            Text(text = "Text 4")
        }
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        Greeting(name = "Hello" )
        MultiRow()
    }

}