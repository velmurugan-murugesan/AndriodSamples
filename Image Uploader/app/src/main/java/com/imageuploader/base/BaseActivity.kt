package com.imageuploader.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.imageuploader.R


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // In Activity's onCreate() for instance

    }

    inline fun <reified T : Activity> Activity.launchActivity(toBundle: Bundle) {
        startActivity(Intent(this, T::class.java),toBundle)
        overridePendingTransitionEnter()
    }

    fun launchActivityWithIntent(intent: Intent,toBundle: Bundle) {
        startActivity(intent,toBundle)
    }

    inline fun <reified T : Activity> Activity.launchActivity() {
        startActivity(Intent(this, T::class.java))
        overridePendingTransitionEnter()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun overridePendingTransitionEnter() = overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)

    open fun overridePendingTransitionExit() = overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)

}