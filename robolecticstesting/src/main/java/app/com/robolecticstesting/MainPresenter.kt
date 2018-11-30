package app.com.robolecticstesting

import android.content.Context

class MainPresenter(mContext: Context) : BasePresenter<MainView>() {

    var userList = mutableListOf<User>()

    fun addUser() {
        if(userList.size > 0){
            val id = userList.last().id
            val user = User(id+1, "User "+(id+1),20 + (id+1),"Chennai")
            userList.add(user)
        } else {
            val user = User(1, "User 1",20,"Chennai")
            userList.add(user)
        }
        getV().updateUsers(userList)
    }

    fun deleteUser() {
        if(userList.size > 0){
            userList.removeAt(userList.size - 1)
            getV().updateUsers(userList)
        }
    }

}