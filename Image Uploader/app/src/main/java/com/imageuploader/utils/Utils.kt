package com.imageuploader.utils

import android.content.Context
import android.content.res.Resources
import android.util.Patterns
import java.nio.charset.Charset
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class Utils {

    companion object {

        fun isValidEmail(email: String?): Boolean {
            var status = false
            email?.let {
                status = it.isNotEmpty()
            }
            return status
        }

        fun isValidPassword(password: String?): Boolean {
            var status = false
            password?.let {
                status = it.isNotEmpty()
                /*if(password.length > 5) {
                    status = true
                }*/
            }
            return status
        }

        fun getLocalBitmapUri(bmp: Bitmap, context: Context): Uri? {
            var bmpUri: Uri? = null
            try {
                val file = File(
                    context.getExternalFilesDir(DIRECTORY_PICTURES),
                    "share_image_" + System.currentTimeMillis() + ".png"
                )
                val out = FileOutputStream(file)
                bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
                out.close()
                bmpUri = Uri.fromFile(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return bmpUri
        }

        fun getAssertFromJson(resources: Resources, location: Int): String? {

            var json: String? = null

            try {
                val inputStream = resources.openRawResource(location)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charset.defaultCharset())

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return json
        }
    }
}
