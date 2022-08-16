package com.example.appnotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.appnotification.MainActivity.Companion.CHANNEL_ID

class App : Application() {

    override fun onCreate() {
        super.onCreate()

     createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // If the Android Version is greater than Oreo,
        // then create the NotificationChannel
        val name = "channel_name"
        val descriptionText = "channel_desc"

        val channel = NotificationChannel(
            CHANNEL_ID,
            name,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = descriptionText
        }

        // Register the channel
        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}