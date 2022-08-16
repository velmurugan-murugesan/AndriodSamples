package com.example.appnotification

import android.app.NotificationManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.appnotification.Utility.sendNotification
import com.example.appnotification.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mMediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.btn).setOnClickListener {
            val notificationManager: NotificationManager = ContextCompat.getSystemService(
                applicationContext,
                NotificationManager::class.java
            ) as NotificationManager

            notificationManager.sendNotification(this)
        }

        stopAudio()




    }


    private fun stopAudio() {
        try {
            mMediaPlayer.release()
        }catch (ex: Exception){
            ex.printStackTrace()
        }

    }

    companion object {
        val CHANNEL_ID = "30"
    }
}