package app.com.bindservice

import android.os.Binder

class MyBinder : Binder() {

    fun getService(): MyBindService {
        return MyBindService()
    }

}