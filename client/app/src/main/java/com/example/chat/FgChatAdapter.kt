package com.example.chat

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.API.Room

class FgChatAdapter(val roomList: ArrayList<Room>): RecyclerView.Adapter<FgChatAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_by_fg_chat, parent, false )
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = roomList[position].name
        holder.lastMsg.text = roomList[position].lastMsg
        holder.datetime.text = roomList[position].datetime
        holder.joinedCnt.text = roomList[position].joinedCnt.toString()
        holder.chatCnt.text = roomList[position].chatCnt.toString()

        holder.view.setOnClickListener {
            var i = Intent(it.context, ChatActivity::class.java)
            i.putExtra("roomName", roomList[position].name)
            it.context.startActivity(i)
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.chat_img) // findViewById: 특정 xml에서 id값으로
        val name = itemView.findViewById<TextView>(R.id.chat_name)
        val lastMsg = itemView.findViewById<TextView>(R.id.chat_msg)
        val datetime = itemView.findViewById<TextView>(R.id.chat_datetime)

        val joinedCnt = itemView.findViewById<TextView>(R.id.chat_join_cnt)
        val chatCnt = itemView.findViewById<TextView>(R.id.chat_msg_cnt)

        val view:View = itemView
    }
}