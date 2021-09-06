package com.velmurugan.mvvmdaggerretrofitkotlin

import com.velmurugan.mvvmdaggerretrofitkotlin.di.DaggerAppComponents
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponents.builder().build()
    }
}