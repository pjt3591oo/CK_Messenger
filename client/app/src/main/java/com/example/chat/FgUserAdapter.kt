package com.example.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chat.API.Friend

class FgUserAdapter(val friendList: ArrayList<Friend>): RecyclerView.Adapter<FgUserAdapter.CustomViewHolder>() {
    private var friends: ArrayList<Friend> = ArrayList<Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_by_fg_user, parent, false)

        return CustomViewHolder(view)
    }


    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: FgUserAdapter.CustomViewHolder, position: Int) {
        Glide.with(holder.img.getContext()).load(friendList.get(position).friend.profile_img).into(holder.img)
        holder.name.text = friendList.get(position).friend.email
        holder.msg.text = friendList.get(position).friend.msg
        holder.sub.text = friendList.get(position).friend.subscribe
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.profile_iv) // findViewById: 특정 xml에서 id값으로
        val name = itemView.findViewById<TextView>(R.id.profile_name)
        val msg = itemView.findViewById<TextView>(R.id.profile_msg)
        val sub = itemView.findViewById<TextView>(R.id.profile_sub)
    }

}