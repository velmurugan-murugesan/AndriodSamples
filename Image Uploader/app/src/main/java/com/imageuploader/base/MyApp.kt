package com.imageuploader.base

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.imageuploader.data.appdata.AppPreference

class MyApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppPreference.init(this)
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}