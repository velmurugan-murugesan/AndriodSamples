package app.com.dagger2mvp

interface MainView : BaseView {

    fun updateUsers(users: List<User>)

}