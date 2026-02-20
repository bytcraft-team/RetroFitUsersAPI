package com.example.retrofitapi.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.retrofitapi.R
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.ActivityDetails
import com.example.retrofitapi.Model.User

class UserAdapter(private var users : List<User>) :
RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val id : TextView = itemView.findViewById(R.id.userId)
        val nom : TextView = itemView.findViewById(R.id.nameId)
        val username : TextView = itemView.findViewById(R.id.usernameId)
        val email : TextView = itemView.findViewById(R.id.emailId)

    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : UserViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user , parent , false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.id.text = user.id.toString()
        holder.nom.text = user.name
        holder.username.text = user.username
        holder.email.text = user.email

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context , ActivityDetails::class.java)
            intent.putExtra("id" , user.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateData(newList: List<User>) {
        users = newList
        notifyDataSetChanged()
    }

}