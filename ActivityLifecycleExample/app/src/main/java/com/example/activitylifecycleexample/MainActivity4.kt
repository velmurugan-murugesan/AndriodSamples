package com.example.activitylifecycleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_4)
        Log.d("MainActivity4","onCreate")
        findViewById<TextView>(R.id.tvActivity4).setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity4","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity4","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity4","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity4","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity4","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity4","onRestart")
    }
}