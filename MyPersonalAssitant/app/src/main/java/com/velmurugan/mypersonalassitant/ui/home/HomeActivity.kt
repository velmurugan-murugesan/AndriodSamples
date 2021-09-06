package com.velmurugan.mypersonalassitant.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.velmurugan.mypersonalassitant.R
import com.velmurugan.mypersonalassitant.utils.VoiceService


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requiredPermission: String = Manifest.permission.RECORD_AUDIO

        // If the user previously denied this permission then show a message explaining why
        // this permission is needed
        if (checkCallingOrSelfPermission(requiredPermission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(requiredPermission), 101)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.startForegroundService(this, Intent(this, VoiceService::class.java))
        } else {
            startService(Intent(this, VoiceService::class.java))
        }

    }
}