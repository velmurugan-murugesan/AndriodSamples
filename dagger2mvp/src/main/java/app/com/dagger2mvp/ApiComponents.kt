package app.com.dagger2mvp

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ApiModule::class])
interface ApiComponents {
    fun inject(mainPresenter: MainPresenter)


    @Component.Builder
    interface Builder {

        fun build() : ApiComponents
        fun apiModule(apiModule: ApiModule): Builder
        fun appModule(appModule: AppModule): Builder
        @BindsInstance
        fun baseView(baseView: BaseView) : Builder

    }

}