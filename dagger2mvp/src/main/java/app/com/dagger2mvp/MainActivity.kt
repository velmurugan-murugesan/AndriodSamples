package app.com.dagger2mvp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    override fun getContext(): Context {
        return this
    }

    private var presenter: MainPresenter? = null
    private var adapter : UserAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter?.attach(this)
        adapter = UserAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter



        button_1.setOnClickListener {
            presenter?.getUserList()
        }
    }

    override fun updateUsers(users: List<User>) {

        if(users.isNotEmpty()){
            adapter?.setUsers(users)
        }

    }
}
