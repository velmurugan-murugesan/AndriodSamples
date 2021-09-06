package com.velmurugan.materialdialog

import android.os.Bundle
import android.system.Os.accept
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.velmurugan.materialdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.alertDialog.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                    .setTitle(resources.getString(R.string.title))
                    .setMessage(resources.getString(R.string.supporting_text))
                    .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                        Toast.makeText(this, "Your are declined the offer", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        Toast.makeText(this, "Thanks you for accepting the offer", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .show()
        }


        binding.simpleDialog.setOnClickListener {
            val items = arrayOf("Chennai", "Mumbai", "Delhi","Banglore","Pune")

            MaterialAlertDialogBuilder(this)
                    .setTitle(resources.getString(R.string.title_simple_dialog))
                    .setItems(items) { dialog, which ->
                        Toast.makeText(this, "Thank you for selecting ${items[which]}", Toast.LENGTH_SHORT).show()
                    }
                    .show()
        }


    }
}