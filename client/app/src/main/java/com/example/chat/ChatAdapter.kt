package com.example.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.chat.API.Msg

/*
*  viewType:
*     (유저타입/메시지타입)
*     10: me/text
*     11: me/image
*     20: you/text
*     21: you/image
* */

class ChatAdapter(val msgList: ArrayList<Msg>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == 10) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_text_send, parent, false)
            return ChatAdapter.SendTextHolder(view)
        } else if(viewType == 11)  {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_img_send, parent, false)
            return ChatAdapter.SendImgHolder(view)
        } else if (viewType == 20) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_text_receive, parent, false)
            return ChatAdapter.ReceiveTextHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_img_receive, parent, false)
            return ChatAdapter.ReceiveReceiveHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getViewType(position)
    }

    fun getViewType (position: Int): Int{
        val userId = 1
        var isSenderMe = msgList[position].sender == userId
        var isText:Boolean = msgList[position].msgType == "text"
        if (isSenderMe) {
            return if (isText) {
                10
            }else {
                11
            }
        } else {
            return if (isText) {
                20
            }else {
                21
            }
        }
    }

    override fun getItemCount(): Int {  return msgList.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewType = getViewType(position)

        if (viewType == 10) {
            (holder as SendTextHolder).msg.text = msgList[position].msg
            holder.dt.text = msgList[position].dt
            holder.readCnt.text = msgList[position].readCnt.toString()
        } else if (viewType == 11) {
            (holder as SendImgHolder)
            Glide.with(holder.msg.context).load(msgList[position].msg).transform(CenterCrop(), RoundedCorners(15)).into(holder.msg)
            holder.dt.text = msgList[position].dt
            holder.readCnt.text = msgList[position].readCnt.toString()
        } else if (viewType == 20) {
            (holder as ReceiveTextHolder).msg.text = msgList[position].msg
            holder.dt.text = msgList[position].dt
            holder.readCnt.text = msgList[position].readCnt.toString()

            holder.name.text = msgList[position].id.toString()
            Glide
                .with(holder.senderImg.context)
                .load(msgList[position].senderImg)
                .transform(CenterCrop(), RoundedCorners(50))
                .into(holder.senderImg)
        } else if (viewType == 21) {
            (holder as ReceiveReceiveHolder)
            Glide.with(holder.msg.context).load(msgList[position].msg).transform(CenterCrop(), RoundedCorners(15)).into(holder.msg)
            holder.dt.text = msgList[position].dt
            holder.readCnt.text = msgList[position].readCnt.toString()
            holder.name.text = msgList[position].id.toString()
            Glide
                .with(holder.senderImg.context)
                .load(msgList[position].senderImg)
                .transform(CenterCrop(), RoundedCorners(50))
                .into(holder.senderImg)
        }
    }

    fun addTextView(t: String): TextView {
        var tv: TextView = TextView(context)
        tv.text = t
        return tv
    }

    fun addImg(url: String): ImageView {
        var iv: ImageView = ImageView(context)
        Glide.with(iv.context).load(url).into(iv)
        return iv
    }

    class SendTextHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_send_text)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_send_text)
        val msg = itemView.findViewById<TextView>(R.id.tv_msg_send_text)
    }

    class SendImgHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_send_img)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_send_img)
        val msg = itemView.findViewById<ImageView>(R.id.iv_msg_send_img)
    }

    class ReceiveTextHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name_receive_text)
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_receive_text)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_receive_text)

        val msg = itemView.findViewById<TextView>(R.id.tv_msg_receive_text)
        val senderImg = itemView.findViewById<ImageView>(R.id.iv_profile_receive_text)
    }

    class ReceiveReceiveHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name_receive_img)
        val dt = itemView.findViewById<TextView>(R.id.tv_datetime_receive_img)
        val readCnt = itemView.findViewById<TextView>(R.id.tv_read_cnt_receive_img)
        val msg = itemView.findViewById<ImageView>(R.id.iv_msg_receive_img)
        val senderImg = itemView.findViewById<ImageView>(R.id.iv_profile_receive_img)
    }


}