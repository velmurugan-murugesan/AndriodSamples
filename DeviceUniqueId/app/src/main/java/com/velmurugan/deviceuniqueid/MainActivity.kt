package com.velmurugan.deviceuniqueid

import android.Manifest
import android.content.Context
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var errorMessage: String = "Error Message : "

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 12)
        }
        
        getList()

    }

    private fun getList() {
        try {
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val imel: String = telephonyManager.imei
            textImelNumber.text = "Imei Number : $imel"
        } catch (e: Exception) {
            errorMessage += "Imei Number : ${e.message}"
            updateErrorMessage()
        }

        try {
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val serial: String = telephonyManager.simSerialNumber
            serialNumber.text = "Serial Number : $serial"
        } catch (e: Exception) {
            errorMessage +=  "Serial Number : ${e.message}"
            updateErrorMessage()
        }


        try {
            val name = Settings.Global.getString(contentResolver, "device_name")
            deviceName.text = "Device Name : $name"
        } catch (e: Exception) {
            errorMessage +=  "Device Name : ${e.message}"
            updateErrorMessage()
        }
    }

    private fun updateErrorMessage() {
        textErrorMessage.text = errorMessage
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getList()
    }
}
