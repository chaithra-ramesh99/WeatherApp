package com.example.weatherapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.container.ImageBitmapString
import com.example.weatherapp.model.User

class UserAdapter(private val context: Context,private var userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false))

    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = userList[position]
        holder.lname.text ="Lname:"+ ItemsViewModel.lname
        holder.name.text ="Fname:"+ ItemsViewModel.name
        holder.email.text = "Email:"+ItemsViewModel.email
        holder.img.setImageBitmap(ImageBitmapString.getBitmapFromStr(ItemsViewModel.image))

        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_userListFragment2_to_weatherFragment)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int =userList.size

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val lname: TextView = itemView.findViewById(R.id.lname)
        val email: TextView = itemView.findViewById(R.id.email)
    }

    fun setData(userList:ArrayList<User>)
    {
        this.userList =userList
        notifyDataSetChanged()
    }
}
