package app.com.bindservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var mServiceBound = false
    var myBindService: MyBindService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { _ ->
            applicationContext.unbindService(mServiceConnection)
        }
    }

    private val mServiceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
            Log.d("mServiceConnection", "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBinder
            myBindService = myBinder.getService()
            mServiceBound = true
            Log.d("mServiceConnection", "onServiceConnected")
        }
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, MyBindService::class.java)
        applicationContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)

    }
}
