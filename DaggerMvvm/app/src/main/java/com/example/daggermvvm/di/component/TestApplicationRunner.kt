package com.example.daggermvvm.di.component

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.daggermvvm.TestApplication

class TestApplicationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application = Instrumentation.newApplication(TestApplication::class.java, context)

}