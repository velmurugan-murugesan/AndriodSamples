package app.com.androidmvpbasic

import android.content.Context

class MainPresenter(mContext: Context) : BasePresenter<MainView>() {

    fun getUserList() {

        val users = mutableListOf<User>()

        users.add(User("Velmurugan", 23, "Chennai"))
        users.add(User("Manchu", 23, "Madurai"))

        getV().updateUsers(users)

    }


}