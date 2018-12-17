package app.com.dagger2mvp

import android.app.Application
import android.content.Context

class MyApp : Application() {

    operator fun get(context: Context): MyApp {
        return context.applicationContext as MyApp
    }
}
