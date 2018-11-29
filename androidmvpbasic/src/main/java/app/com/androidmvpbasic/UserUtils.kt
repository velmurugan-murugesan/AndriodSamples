package app.com.androidmvpbasic

open class UserUtils{

    val users = mutableListOf<User>()

    fun addUser(user: User){
       users.add(user)
    }



}
