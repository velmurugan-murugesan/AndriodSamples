package com.velmurugan.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velmurugan.myapplication.ui.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                    Greeting("Android")
            }

        }
    }
}
@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello $name!")
        /*Text(text = "Velmurugan", style = MaterialTheme.typography.body1)
        Text(text = "Velmurugan", style = MaterialTheme.typography.body2)
        Text(text = "Velmurugan", style = MaterialTheme.typography.button)
        Text(text = "Velmurugan", style = MaterialTheme.typography.caption)
        Text(text = "Velmurugan", style = MaterialTheme.typography.h3)
        Text(text = "Velmurugan", style = MaterialTheme.typography.h4)
        Text(text = "Velmurugan", style = MaterialTheme.typography.h6)
        Text(text = "Velmurugan", style = MaterialTheme.typography.subtitle1)
        Text(text = "Velmurugan", style = MaterialTheme.typography.subtitle2)*/
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}