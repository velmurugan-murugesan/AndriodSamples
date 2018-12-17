package app.com.bindservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import app.com.bindservice.MyBinder

class MyBindService : Service() {

    var iBinder: MyBinder? = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d("MyBindService","onCreate")
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d("MyBindService","onBind")
        return iBinder
    }


    fun getData() : String {
        return "Data"
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MyBindService","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyBindService","onDestroy")

    }


}