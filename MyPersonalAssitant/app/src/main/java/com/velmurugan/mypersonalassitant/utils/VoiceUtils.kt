package com.velmurugan.mypersonalassitant.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.velmurugan.mypersonalassitant.R

object VoiceUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun startNotification(
        context: Context,
        notificationChannelId: String
    ): NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(context, notificationChannelId)
            .setOngoing(false)
            .setSmallIcon(R.drawable.ic_launcher_background);
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            notificationChannelId,
            notificationChannelId,
            NotificationManager.IMPORTANCE_LOW
        );
        notificationChannel.description = notificationChannelId;
        notificationChannel.setSound(null, null);
        notificationManager.createNotificationChannel(notificationChannel);
        return builder
    }

    fun getResultFromBundle(results: Bundle): String {
        var str = ""
        val data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (data != null) {
            for (i in 0 until data.size) {
                str = data[0]
            }
            return str
        }
        return str
    }

    fun resetVolume(audioManager: AudioManager, volume: Int) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, volume, 0)
    }

    fun startListening(
        context: Context,
        audioManager: AudioManager,
        textToSpeechSystem: TextToSpeech
    ) {
        val sr = SpeechRecognizer.createSpeechRecognizer(context)
        sr.setRecognitionListener(VoiceService.VoiceListener(context, textToSpeechSystem))
        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
            "en"
        );
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_CALLING_PACKAGE,
            context.packageName
        );
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        );
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        sr.startListening(recognizerIntent)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0)
    }


}