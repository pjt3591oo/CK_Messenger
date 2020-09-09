package com.example.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.chat.API.Friend


class FgUserAdapter(
    val activity: FragmentActivity,
    val friendList : ArrayList<ArrayList<Friend>>,
    val nameList   : ArrayList<String>

): RecyclerView.Adapter<FgUserAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FgUserAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_by_fg_user, parent, false)

        return FgUserAdapter.CustomViewHolder(view)
    }


    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: FgUserAdapter.CustomViewHolder, position: Int) {
        val friend: ArrayList<Friend> = friendList.get(position)
        val name: String = nameList.get(position)
        if(name != "내 프로필") {
            holder.friendProfileTitle_tv.text = name
        }
        holder.friendsProfile_rv.setHasFixedSize(true)
        holder.friendsProfile_rv.layoutManager = LinearLayoutManager(activity)
        holder.friendsProfile_rv.adapter = FgUserSectionAdpater(friend)

//        (holder.friendsProfile_rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        holder.friendProfileTitle_tv.setOnClickListener {
            println(1.toString())
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val img = itemView.findViewById<ImageView>(R.id.chat_img) // findViewById: 특정 xml에서 id값으로
//        val name = itemView.findViewById<TextView>(R.id.chat_name)
//        val msg = itemView.findViewById<TextView>(R.id.chat_msg)
//        val sub = itemView.findViewById<TextView>(R.id.chat_datetime)
        val friendProfileTitle_tv = itemView.findViewById<TextView>(R.id.friend_profile_title)
        val friendsProfile_rv = itemView.findViewById<RecyclerView>(R.id.friends_profile)
    }
}