package com.velmurugan.fitnessapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.*
import android.util.Log
import android.widget.Toast
import java.util.*

class SyncReceiver : BroadcastReceiver(), SensorEventListener {

    private var context: Context? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("SyncReceiver", "onReceive: alarm called ")
        context?.let {
            this.context = context
           /* val listener = object: TriggerEventListener() {
                override fun onTrigger(event: TriggerEvent?) {
                    val stepsCount = StepsCount(steps = event!!.values[0].toInt(), date = System.currentTimeMillis().toString())
                    *//*val lastDayCount = AppDatabase.getInstance(context).userDao().getUserDetails().last()
                    val totalSteps = event.values[0].toInt()
                    val todaySteps = totalSteps.minus(lastDayCount.steps!!)*//*
                    AppDatabase.getInstance(context).userDao().insertToRoomDatabase(stepsCount)
                }
            }
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
            if (sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) { // success! we have an accelerometer
                val stepCounter = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                stepCounter?.let {
                    sensorManager.requestTriggerSensor(listener, stepCounter);
                }
            }*/
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            stepCounterSensor?.let {
                sensorManager.registerListener(this@SyncReceiver, it, SensorManager.SENSOR_DELAY_FASTEST)
            }

        }

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent ?: return
        Log.d("Synce Meae", "onSensorChanged ${sensorEvent.toString()}")
        sensorEvent.values.firstOrNull()?.let {
            val stepsCount = StepsCount(steps = sensorEvent!!.values[0].toInt(), date = System.currentTimeMillis().toString())
            context?.let {
                AppDatabase.getInstance(it).userDao().insertToRoomDatabase(stepsCount)
            }
            val sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorManager.unregisterListener(this)

            val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, SyncReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 100, intent, 0)
            }
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar[Calendar.HOUR_OF_DAY] = calendar[Calendar.HOUR_OF_DAY]
            calendar[Calendar.MINUTE] = calendar[Calendar.MINUTE] + 1
            calendar[Calendar.SECOND] = 0

            alarmMgr.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmIntent
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}