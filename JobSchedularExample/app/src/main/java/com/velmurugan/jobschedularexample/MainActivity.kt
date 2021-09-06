package com.velmurugan.jobschedularexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonStartJob)

        button.setOnClickListener {
            val componentName = ComponentName(this, MyJob::class.java)
            val info = JobInfo.Builder(123, componentName)
                    .setRequiresCharging(true)
                    .setPersisted(true)
                    .build()

            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val status = jobScheduler.schedule(info)

            if (status == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "onCreate: Result Success")
            } else {
                Log.d(TAG, "onCreate: Result Failed")
            }

        }
    }
}