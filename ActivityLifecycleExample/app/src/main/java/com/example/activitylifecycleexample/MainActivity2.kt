package com.example.activitylifecycleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)
        Log.d("MainActivity2","onCreate")
        findViewById<TextView>(R.id.tvActivity2).setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("MainActivity2","onNewIntent")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity2","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity2","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity2","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity2","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity2","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity2","onRestart")
    }
}