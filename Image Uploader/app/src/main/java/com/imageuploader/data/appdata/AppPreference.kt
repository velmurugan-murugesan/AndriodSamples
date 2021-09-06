package com.imageuploader.data.appdata

import android.content.Context
import android.content.SharedPreferences
import java.lang.IllegalArgumentException

object AppPreference {

    private lateinit var appPreference : SharedPreferences

    fun init(context: Context) {
        appPreference = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
    }

    fun saveData(key: String, value: Any?) = with(appPreference.edit()) {

        when(value) {
            is Int -> putInt(key,value)
            is String -> putString(key,value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("Shared preference only accept Primitive data types")

        }.apply()

    }

    fun getStringValue(key: String) = appPreference.getString(key, null)

    fun getBooleanValue(key: String) = appPreference.getBoolean(key, false)


    fun getIntValue(key: String) = appPreference.getInt(key, 0)

    fun clearData() = appPreference.edit().clear().apply()

}