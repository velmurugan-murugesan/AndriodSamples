package app.com.robolecticstesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.com.androidmvpbasic.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

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

        button_add.setOnClickListener {
            presenter?.addUser()
        }

        button_delete.setOnClickListener {
            presenter?.deleteUser()
        }
    }

    override fun updateUsers(users: List<User>) {
            adapter?.setUsers(users)
    }
}
