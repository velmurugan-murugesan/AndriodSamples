package app.com.dragger2

import dagger.Component
import javax.inject.Singleton


@Component(modules = [ApiModule::class, AppModule::class])
@Singleton
public interface ApiComponent {
    fun inject(activity: MainActivity)
}