package io.github.aiwithab.crudroom.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import io.github.aiwithab.crudroom.R
import io.github.aiwithab.crudroom.models.UserModel


class UserListAdapter : RecyclerView.Adapter<MyViewHolder>() {

    var userList = mutableListOf<UserModel>()

    var clickListener: ListClickListener<UserModel>? = null

    fun setUsers(users: List<UserModel>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = userList[position]
        holder.userName.text = user.userName
        holder.fullName.text = user.fullName
        holder.address.text = user.address
        holder.gender.text = user.gender
        holder.mobileNumber.text = user.mobileNumber
        holder.layout.setOnClickListener {
            clickListener?.onClick(user,position)
        }

        holder.imgDelete.setOnClickListener {
            clickListener?.onDelete(user)
        }

    }

    fun setOnItemClick(listClickListener: ListClickListener<UserModel>) {
        this.clickListener = listClickListener
    }

}

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val userName: TextView = view.findViewById(R.id.text_username)
    val fullName: TextView = view.findViewById(R.id.text_fullName)
    val gender: TextView = view.findViewById(R.id.text_gender)
    val mobileNumber: TextView = view.findViewById(R.id.text_mobileNumber)
    val address: TextView = view.findViewById(R.id.text_address)
    val layout: ConstraintLayout = view.findViewById(R.id.user_layout)
    val imgDelete: ImageView = view.findViewById(R.id.imgDelete)
}


interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}