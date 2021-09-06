package com.velmurugan.fitnessapp

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.velmurugan.fitnessapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), SensorEventListener,NavigationBarView.OnItemSelectedListener  {

    var running = false
    var sensorManager: SensorManager? = null
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)


        binding.circularProgressBar.progressMax = 1500f
        //binding.circularProgressBar.setProgressWithAnimation(500f, 1000, AccelerateInterpolator())

        binding.cardStepCount.setOnClickListener {
            Log.d("MainAc", "onCreate: ${AppDatabase.getInstance(this@MainActivity).userDao().getUserDetails().size}")
        }

        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(
                Manifest.permission.ACTIVITY_RECOGNITION
            ),
            102
        ) //loading the default fragment
        //loading the default fragment
        loadFragment(HomeFragment()) //getting bottom navigation view and attaching the listener
        //getting bottom navigation view and attaching the listener
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    private fun startBackgroundJob() {

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(this@MainActivity, SyncJobService::class.java)
        val jobInfo = JobInfo.Builder(12, componentName)
            .setPeriodic(15 * 60 * 1000)
            .build()
        jobScheduler.schedule(jobInfo)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.getItemId()) {
            R.id.page_1 -> fragment = HomeFragment()
            R.id.page_2 -> fragment = HomeFragment()
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean { //switching fragment
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
        setAlermManager()
        //startBackgroundJob()
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {

            val totalCount = AppDatabase.getInstance(this@MainActivity).userDao().getUserDetails()
            if (totalCount.isNotEmpty()) {
                val lastDay = totalCount.first()
                val today = event.values[0] - lastDay.steps!!
                binding.circularProgressBar.setProgressWithAnimation(today, 1000, AccelerateInterpolator())
                binding.todayStepsCount.text = "" + today.toInt()
            } else {
                val stepsCount = StepsCount(steps = event!!.values[0].toInt(), date = System.currentTimeMillis().toString())
                AppDatabase.getInstance(this@MainActivity).userDao().insertToRoomDatabase(stepsCount)
                val totalCount = AppDatabase.getInstance(this@MainActivity).userDao().getUserDetails()
                val lastDay = totalCount.first()
                val today = event.values[0] - lastDay.steps!!
                binding.todayStepsCount.text = "" + today.toInt()
                binding.circularProgressBar.setProgressWithAnimation(today, 1000, AccelerateInterpolator())
            }

        }
    }


    private fun setAlermManager() {
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this@MainActivity, SyncReceiver::class.java).let { intent ->
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent.getBroadcast(this@MainActivity, 100, intent, 0)
        }
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 17 // MIDNIGHT 12 AM
        calendar[Calendar.MINUTE] = 35
        calendar[Calendar.SECOND] = 0

        alarmMgr.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )

    }

}