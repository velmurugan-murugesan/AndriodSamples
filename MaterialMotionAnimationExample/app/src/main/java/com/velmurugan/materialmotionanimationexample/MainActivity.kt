package com.velmurugan.materialmotionanimationexample

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialFadeThrough
import com.velmurugan.materialmotionanimationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(mainBinding.root)

        mainBinding.bottomBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    findNavController(R.id.navController).navigateUp()
                    true
                }
                R.id.search -> {

                    val materialFade = com.google.android.material.transition.platform.MaterialFadeThrough().apply {
                    addTarget(mainBinding.bottomBar)
                    }
                    window.exitTransition = materialFade


                    findNavController(R.id.navController).navigate(R.id.action_homeFragment_to_searchFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}