package app.com.timertaskexample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var handler: Handler? = null
    var autoConnected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_connect.setOnClickListener {

            autoConnected = true
            connect()
        }

        button_disconnect.setOnClickListener {
            autoConnected = false
            disconnect()
        }

    }

    private val runnable = Runnable {
        Log.d("Task", "Running")
        call()
    }


    private fun call() {
        if(autoConnected){
            handler?.removeCallbacks(null)
            handler!!.postDelayed(runnable, 2000)
        } else {

        }
    }

    private fun connect() {
        if(handler == null){
            handler = Handler()
        }
        handler?.removeCallbacks(null)
        handler!!.postDelayed(runnable, 2000)
    }

    private fun disconnect() {
        handler?.removeCallbacks(null)
        handler = null
    }
}
