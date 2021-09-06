package com.velmurugan.androidanimationexample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.velmurugan.androidanimationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Container transformation
    // Fade
    // Fade through
    //Shared Axis

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}