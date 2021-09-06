package com.velmurugan.jobschedularexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*

class MyJob : JobService() {
    private val NOTIFICATION_CHANNEL_ID = "my_notification_location"
    private val TAG = "MyJob"
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: started")
        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID).setOngoing(false).setSmallIcon(R.drawable.ic_launcher_background)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)
            notificationChannel.description = NOTIFICATION_CHANNEL_ID
            notificationChannel.setSound(null, null)
            notificationManager.createNotificationChannel(notificationChannel)
            startForeground(1, builder.build())
        }
        var count = 0
        val timer = Timer()
        var timerTask: TimerTask? = null
        timerTask = object : TimerTask() {
            override fun run() {

                Thread {
                    count += 1
                    Log.d(TAG, "run: Count $count")
                }.start()
            }
        }
        timer.scheduleAtFixedRate(timerTask, 0, 2000)

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob: stoped")
        return true
    }


}