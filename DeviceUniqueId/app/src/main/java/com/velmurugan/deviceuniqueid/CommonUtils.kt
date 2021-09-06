package com.velmurugan.deviceuniqueid

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker

inline fun onlyTry(fun1: () -> Unit) {
    try {
        fun1()
    } catch (e: Exception) {}
}

inline fun runWithPhoneReadPermission( activity: Activity,fun1 : () -> Unit) {
    if (checkWritePermission(activity)) {
        fun1()
    } else {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_PHONE_STATE), 3)
    }
}

fun checkWritePermission(context: Context): Boolean {
    return (PermissionChecker.checkSelfPermission(
            context, Manifest.permission.READ_PHONE_STATE
    ) == PermissionChecker.PERMISSION_GRANTED)
}