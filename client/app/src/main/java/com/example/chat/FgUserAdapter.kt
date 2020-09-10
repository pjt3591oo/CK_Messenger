package com.example.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.API.Friend


class FgUserAdapter(
    val activity: FragmentActivity,
    val friendList : ArrayList<ArrayList<Friend>>,
    val nameList   : ArrayList<String>

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        var view: View

        if (viewType == 0) {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.my_by_fg_user, parent, false)
            return FgUserAdapter.MyViewHolder(view)
        } else {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.friend_by_fg_user, parent, false)
            return FgUserAdapter.FriendViewHolder(view)
        }
    }


    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friend: ArrayList<Friend> = friendList.get(position)
        val name: String = nameList.get(position)

        val isMyProfile: Boolean = position == 0
        if(isMyProfile) {
            (holder as MyViewHolder).myProfile_rv.setHasFixedSize(true)
            holder.myProfile_rv.layoutManager = LinearLayoutManager(activity)
            holder.myProfile_rv.adapter = FgUserSectionAdpater(friend)
        } else {
            (holder as FriendViewHolder).friendProfileTitle_tv.text = name
            holder.friendsProfile_rv.setHasFixedSize(true)
            holder.friendsProfile_rv.layoutManager = LinearLayoutManager(activity)
            holder.friendsProfile_rv.adapter = FgUserSectionAdpater(friend)

            holder.friendProfileTitle_tv.setOnClickListener {
                Log.i("[position]", position.toString())
            }
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val myProfile_rv = itemView.findViewById<RecyclerView>(R.id.my_profile)
    }

    class FriendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val friendProfileTitle_tv = itemView.findViewById<TextView>(R.id.friend_profile_title)
        val friendsProfile_rv = itemView.findViewById<RecyclerView>(R.id.my_profile)
    }

    class ViewType {
        val MY_PROFILE = 0
        val FRIEND_PRIFILE = 1
    }
}