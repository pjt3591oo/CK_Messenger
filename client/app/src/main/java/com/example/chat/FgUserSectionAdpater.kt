package com.example.chat


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chat.API.Friend

class FgUserSectionAdpater(val friendList: ArrayList<Friend>): RecyclerView.Adapter<FgUserSectionAdpater.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FgUserSectionAdpater.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fg_user_section_profile, parent, false)

        return FgUserSectionAdpater.CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun getItemViewType(position: Int): Int {
        return friendList.get(position).id
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Glide.with(holder.img.context).load(friendList[position].friend.profile_img).into(holder.img)
        holder.name.text = friendList[position].friend.email
        holder.msg.text = friendList[position].friend.msg
        holder.sub.text = friendList[position].friend.subscribe

        holder.view.setOnClickListener {
            val isMyProfile: Boolean = friendList[position].id == 1

            var movedIntent: Intent = if (isMyProfile) {
                Intent(it.context, MyProfileActivity::class.java)
            } else {
                Intent(it.context, FriendProfileActivity::class.java)
            }

            it.context.startActivity(movedIntent)
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val img = itemView.findViewById<ImageView>(R.id.chat_img) // findViewById: 특정 xml에서 id값으로
        val name = itemView.findViewById<TextView>(R.id.chat_name)
        val msg = itemView.findViewById<TextView>(R.id.chat_msg)
        val sub = itemView.findViewById<TextView>(R.id.chat_datetime)
    }
}