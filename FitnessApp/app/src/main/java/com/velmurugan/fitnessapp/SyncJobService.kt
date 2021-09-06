package com.velmurugan.fitnessapp

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.hardware.*
import android.os.SystemClock
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class SyncJobService : JobService(), SensorEventListener {

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        printLog("DemoJobService runs")
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        stepCounterSensor?.let {
            sensorManager.registerListener(this@SyncJobService, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
        return true;
    }

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        return true
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        printLog("onAccuracyChanged: Sensor: $sensor; accuracy: $accuracy")
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent ?: return
        printLog("onSensorChanged: sensorEvent: ${sensorEvent.toString()}")
        sensorEvent.values.firstOrNull()?.let {

            val stepsCount = StepsCount(steps = sensorEvent!!.values[0].toInt(), date = System.currentTimeMillis().toString())

            AppDatabase.getInstance(this@SyncJobService).userDao().insertToRoomDatabase(stepsCount)
/*
            // Data 2: The number of nanosecond passed since the time of last boot
            val lastDeviceBootTimeInMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            val sensorEventTimeInNanos = sensorEvent.timestamp // The number of nanosecond passed since the time of last boot
            val sensorEventTimeInMillis = sensorEventTimeInNanos / 1000_000

            val actualSensorEventTimeInMillis = lastDeviceBootTimeInMillis + sensorEventTimeInMillis
            val displayDateStr = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(actualSensorEventTimeInMillis)
            printLog("Sensor event is triggered at $displayDateStr")


            printLog("Step count: $it ")
            val timeInMills = System.currentTimeMillis() + (sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()) / 1000000

            val timeInMillis: Long =
                (Date().getTime() + (sensorEvent.timestamp - System.nanoTime()) / 1000000L)
            val stepsCount = StepsCount(
                steps = sensorEvent!!.values[0].toInt(), lastReboot =displayDateStr,
                date = System.currentTimeMillis().toString())
            AppDatabase.getInstance(this@SyncJobService).userDao().insertToRoomDatabase(stepsCount)*/
            val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorManager.unregisterListener(this@SyncJobService)
        }
    }

    fun printLog(msg : String) {
        Log.d("Sync Job service", "printLog: ${msg}")
    }
}