package com.example.appnotification

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.media.RingtoneManager.*
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.appnotification.MainActivity.Companion.CHANNEL_ID
import com.example.appnotification.Utility.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

    //this is called when a message is received
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val title = remoteMessage.data["title"]
        val body = remoteMessage.data["body"]

        /*// Create an explicit intent for an Activity in your app
        val intent = Intent(this, MainActivity::class.java)
        // If you need, set the intent flag for the activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Save the RemoteMessage object as extra data
        // Flag FLAG_ONE_SHOT indicates that this PendingIntent can be used only once
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setColor(ContextCompat.getColor(this, R.color.teal_200))
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this)) {
            notify(1, notificationBuilder.build())
        }*/
        createNotificationChannel(this)
        val snoozeIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
            action = "ac"
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            // Set the intent that will fire when the user taps the notification

            .setAutoCancel(true)
            .addAction(
                com.google.android.material.R.drawable.ic_clock_black_24dp, "Snooze",
                pendingIntent)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.notify(1, builder.build())

        //sendNotification(body ?: " Empty body")
    }


    override fun onNewToken(token: String) {

        Log.d("rfst", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)

    }

    private fun sendRegistrationToServer(token: String?) {

        //you can send the updated value of the token to your server here

    }

    private fun sendNotification(
        messageBody: String
    ) {
        val notificationManager: NotificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(
           applicationContext
        )
    }

    private fun sendPushNotification(title: String, body: String) {

    }


    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = "c_name"
        val descriptionText = "c_desc"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val uri: Uri =
            getDefaultUri(TYPE_NOTIFICATION) // To get the URI of default notification uri
        val uriw: Uri = Uri.parse("android.resource://com.example.appnotification/" + R.raw.alarm);
        val audioAttributes =
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
            enableLights(true)
            enableVibration(true)
            setSound(null, null)
        }
        playAudio(this, "alarm")

        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    private var mMediaPlayer: MediaPlayer = MediaPlayer()

    private fun stopAudio() {
        try {
            mMediaPlayer.release()
        }catch (ex: Exception){
            ex.printStackTrace()
        }

    }
    fun playAudio(mContext: Context, fileName: String) {
        try {
            stopAudio()
            mMediaPlayer = MediaPlayer.create(mContext, mContext.resources.getIdentifier(fileName, "raw", mContext.packageName))
            mMediaPlayer.setOnCompletionListener {  stopAudio() }

            mMediaPlayer.setLooping(true);

            mMediaPlayer.start()
        }catch (ex: Exception){
            ex.printStackTrace()
        }

    }
}
