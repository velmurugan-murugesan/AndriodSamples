package app.com.dagger2mvp

open class BasePresenter<T: BaseView>(view: MainView) {

    lateinit var view : T

    private val injector: ApiComponents = DaggerApiComponents
            .builder()
            .appModule(AppModule())
            .apiModule(ApiModule())
            .baseView(view)
            .build()


    init {
        inject()
    }


    fun attach(v: T){
        view = v
    }

    fun getV(): T {
        return view
    }
    private fun inject() {
        when (this) {
            is MainPresenter -> injector.inject(this)
        }
    }

}