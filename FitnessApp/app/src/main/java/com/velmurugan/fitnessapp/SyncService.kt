package com.velmurugan.fitnessapp

import android.app.Service
import android.content.Intent
import android.hardware.*
import android.os.IBinder

class SyncService : Service() {

    var sensorManager: SensorManager? = null

    private val listener = object: TriggerEventListener() {
        override fun onTrigger(event: TriggerEvent?) {
            val stepsCount = StepsCount(steps = event!!.values[0].toInt(), date = System.currentTimeMillis().toString())
            AppDatabase.getInstance(this@SyncService).userDao().insertToRoomDatabase(stepsCount)

        }
    }
    override fun onCreate() {
        /*sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        if (sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) { // success! we have an accelerometer
            val stepCounter = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            stepCounter?.let {
                sensorManager?.requestTriggerSensor(listener, stepCounter);

            }
        }*/
        val stepsCount = StepsCount(steps = 20, date = System.currentTimeMillis().toString())

        AppDatabase.getInstance(this@SyncService).userDao().insertToRoomDatabase(stepsCount)

    }



    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}