package com.example.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chat.API.Msg

class ChatAdapter(val msgList: ArrayList<Msg>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_send, parent, false)
            return ChatAdapter.SendHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_receive, parent, false)
            return ChatAdapter.ReceiveHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val userId = 1
        return if (msgList[position].sender == userId) {
            1
        } else {
            0
        }
    }

    override fun getItemCount(): Int {  return msgList.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userId = 1
        val isUser: Boolean = msgList[position].sender == userId

        if (isUser) {
            (holder as SendHolder).readCnt.text = msgList[position].readCnt.toString()
            holder.dt.text = msgList[position].dt.toString()
            holder.msg.text = msgList[position].msg
        } else {
            (holder as ReceiveHolder).name.text =msgList[position].sender.toString()
            Glide.with(holder.img.context).load(msgList[position].senderImg).into(holder.img)
            holder.readCnt.text = msgList[position].readCnt.toString()
            holder.dt.text = msgList[position].dt
            holder.msg.text = msgList[position].msg
        }
    }

    class ReceiveHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.iv_profile_receive)
        val name = itemView.findViewById<TextView>(R.id.tv_name_receive)
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_receive)
        val msg = itemView.findViewById<TextView>(R.id.tv_msg_receive)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_reveice)
    }

    class SendHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_send)
        val msg = itemView.findViewById<TextView>(R.id.tv_msg_send)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_send)
    }
}