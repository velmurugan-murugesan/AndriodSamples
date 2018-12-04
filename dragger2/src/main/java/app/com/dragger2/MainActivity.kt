package app.com.dragger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerApiComponent.builder()
                .appModule(AppModule(MyApp().get(this)))
                .apiModule(ApiModule())
                .build()
                .inject(this)


        val all = retrofit.create(Api::class.java).getAll()


        all.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Failed = ", t.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("Response = ", response.body().toString())
            }
        })
    }


}


