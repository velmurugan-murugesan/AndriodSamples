package com.example.activitylifecycleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_3)
        Log.d("MainActivity3","onCreate")
        findViewById<TextView>(R.id.tvActivity3).setOnClickListener {
            val intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvLaunchActivity3).setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("MainActivity3","onNewIntent")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity3","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity3","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity3","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity3","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity3","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity3","onRestart")
    }
}