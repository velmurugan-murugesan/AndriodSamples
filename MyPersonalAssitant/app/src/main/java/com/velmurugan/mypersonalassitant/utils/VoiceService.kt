package com.velmurugan.mypersonalassitant.utils

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.annotation.RequiresApi

class VoiceService : Service(), TextToSpeech.OnInitListener {
    private val notificationChannelId = "my_notification_location"
    private lateinit var textToSpeechSystem: TextToSpeech
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        textToSpeechSystem = TextToSpeech(this, this)
        startForeground(1, VoiceUtils.startNotification(this, notificationChannelId).build())
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        VoiceUtils.startListening(this, audioManager, textToSpeechSystem)
        return START_STICKY;
    }

    internal class VoiceListener(private val context: Context, private val textToSpeechSystem: TextToSpeech) : RecognitionListener {
        private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var streamVolume = 0
        override fun onReadyForSpeech(params: Bundle) {
            VoiceUtils.resetVolume(audioManager, streamVolume)
        }

        override fun onBeginningOfSpeech() {
        }

        override fun onRmsChanged(rmsdB: Float) {
        }

        override fun onBufferReceived(buffer: ByteArray) {
        }

        override fun onEndOfSpeech() {
        }

        override fun onError(error: Int) {
            VoiceUtils.startListening(context, audioManager, textToSpeechSystem)
        }

        override fun onResults(results: Bundle) {
            val voiceResult = VoiceUtils.getResultFromBundle(results)
            if (voiceResult.isNotEmpty()) {
                val ownerName = voiceResult.split(" ")
                if (ownerName.size >= 1 && ownerName[0] == AppConstants.assistantName) {
                    textToSpeechSystem.speak(" Hello ${AppConstants.assistantName}", TextToSpeech.QUEUE_ADD, null, null);
                } else if (ownerName.size >= 2 && ownerName[1] == AppConstants.assistantName) {
                    textToSpeechSystem.speak(" Hello ${AppConstants.assistantName}", TextToSpeech.QUEUE_ADD, null, null);
                } else {
                    VoiceUtils.startListening(context, audioManager, textToSpeechSystem)
                }
            }
        }

        override fun onPartialResults(partialResults: Bundle) {
        }

        override fun onEvent(eventType: Int, params: Bundle) {
        }
    }

    override fun onDestroy() {
        textToSpeechSystem?.stop();
        textToSpeechSystem?.shutdown();
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeechSystem.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
                    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    VoiceUtils.startListening(this@VoiceService, audioManager, textToSpeechSystem)
                }

                override fun onError(utteranceId: String) {}
                override fun onStart(utteranceId: String) {
                    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

                    VoiceUtils.resetVolume(audioManager, 10)
                }
            })
        }
    }
}