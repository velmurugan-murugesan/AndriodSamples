package app.com.dagger2mvp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {

    @Inject lateinit var api: Api

    fun getUserList() {

        api.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Failed", t.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("Success", response.body().toString())
            }
        })

        val users = mutableListOf<User>()

        users.add(User("Velmurugan",  "Chennai"))
        users.add(User("Manchu",  "Madurai"))

        getV().updateUsers(users)

    }


}