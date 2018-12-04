package app.com.dragger2

import android.app.Application
import android.content.Context

class MyApp : Application() {

    private var apiComponent: ApiComponent? = null

    operator fun get(context: Context): MyApp {
        return context.applicationContext as MyApp
    }

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule())
                .build()
    }

    fun getApiComponents() : ApiComponent {
        return apiComponent!!
    }

}