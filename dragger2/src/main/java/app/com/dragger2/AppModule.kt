package app.com.dragger2

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {


    @Provides
    @Singleton
    fun provideApplication() : Application {
        return application
    }


}