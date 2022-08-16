package com.example.appnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.Settings.Global.getString
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.appnotification.MainActivity.Companion.CHANNEL_ID


object Utility {

    // Notification ID.


    fun NotificationManager.sendNotification(context: Context) {
        val expandedView = RemoteViews(context.packageName, R.layout.notification_layout_expanded)
        /*expandedView.setTextViewText(
            R.id.timestamp,
            DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
        )
        expandedView.setTextViewText(R.id.notification_message, mEditText.getText())
        // adding action to left button
        val leftIntent = Intent(this, NotificationIntentService::class.java)
        leftIntent.action = "left"
        expandedView.setOnClickPendingIntent(
            R.id.left_button,
            PendingIntent.getService(this, 0, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        )
        // adding action to right button
        val rightIntent = Intent(this, NotificationIntentService::class.java)
        rightIntent.action = "right"
        expandedView.setOnClickPendingIntent(
            R.id.right_button,
            PendingIntent.getService(this, 1, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        )
        val collapsedView = RemoteViews(getPackageName(), R.layout.view_collapsed_notification)
        collapsedView.setTextViewText(
            R.id.timestamp,
            DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
        )*/
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(
                context,
                CHANNEL_ID
            ) // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText("Content text") // notification will be dismissed when tapped
                .setAutoCancel(true) // tapping notification will open MainActivity
                .setSound(alarmSound)
                .setContentIntent(
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, MainActivity::class.java), 0
                    )
                ) // setting the custom collapsed and expanded views
                .setCustomBigContentView(expandedView) // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        // retrieves android.app.NotificationManager
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.notify(0, builder.build())
    }


    fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {


        // TODO: Step 1.11 create intent
        val contentIntent = Intent(applicationContext, MainActivity::class.java)

        // TODO: Step 1.12 create PendingIntent
        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            291,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // TODO: You can add style here

        val notificationLayout =
            RemoteViews(applicationContext.packageName, R.layout.notification_layout)

        // TODO: Step 1.2 get an instance of NotificationCompat.Builder

        // Build the notification
        val builder = NotificationCompat.Builder(
            applicationContext,
            // TODO: Step 1.8 use a notification channel
            applicationContext.getString(R.string.app_notification_channel_id)
        )
            // TODO: Step 1.3 set title, text and icon to builder
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            // TODO: Step 1.13 set content intent
            .setContentIntent(contentPendingIntent)
            .setCustomContentView(notificationLayout)
            // TODO: Step 2.5 set priority
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // TODO Step 1.4 call notify
        // Deliver the notification
        notify(291, builder.build())
    }

// TODO: Step 1.14 Cancel all notifications
    /**
     * Cancels all notifications.
     *
     */
    fun NotificationManager.cancelNotifications() {
        cancelAll()
    }



}