package com.velmurugan.camerasample

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.velmurugan.camerasample.databinding.DialogImageCaptureBinding
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private val modelDialogBinding by lazy {
        DialogImageCaptureBinding.inflate(LayoutInflater.from(this), null, false)
    }
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.buttonShowDialog)

        button.setOnClickListener {
            val customDialog = AlertDialog.Builder(this).create()

            customDialog.apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setView(modelDialogBinding.root)
                setCancelable(false)
            }.show()


            modelDialogBinding.imageView.setOnClickListener {
                ImagePicker.with(this)
                    .galleryOnly()
                    .start()
            }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
            modelDialogBinding.imageView.setImageURI(uri)
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            val byteArray: ByteArray =
                outputStream.toByteArray() //Use your Base64 String as you wish
            //Use your Base64 String as you wish
            val encodedString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            Log.d("TAG", "onActivityResult: $encodedString")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun getPathFromURI(contentUri: Uri?): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = contentResolver.query(contentUri!!, proj, null, null, null)!!
        if (cursor.moveToFirst()) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }
}