package app.com.dagger2mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.com.dagger2mvp.UserAdapter.MyViewHolder

class UserAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var users = mutableListOf<User>()

    fun setUsers(items: List<User>){
        users = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.user_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        val user = users[p1]
        p0.tvName.text = user.name
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName:TextView = itemView.findViewById(R.id.tv_name)

    }

}