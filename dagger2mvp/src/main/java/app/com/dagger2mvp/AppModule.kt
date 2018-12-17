package app.com.dagger2mvp

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {




    @Provides
    @Singleton
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

}