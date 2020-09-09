package com.example.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.API.Friend
import com.example.chat.API.Room
import com.example.chat.R

class Chats: Fragment() {
    var fgChatAdapter: FgChatAdapter? = null
    var rv_chat: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fg_chat, container, false)

        rv_chat = view.findViewById(R.id.rv_chat);
        rv_chat?.setHasFixedSize(true)
        rv_chat?.layoutManager = LinearLayoutManager(activity)

        var data: ArrayList<Room> = ArrayList<Room>()
        data.add(Room(1, "", "room1", "안녕하세요", "17:05:11", 2, 14))
        data.add(Room(2, "", "room2", "안녕하세요", "22:05:11", 114, 4))
        data.add(Room(3, "", "room3", "너무 심심하다!!!", "9:05:11", 2, 4))
        data.add(Room(4, "", "room4", "안녕", "8:05:11", 2, 1))
        data.add(Room(5, "", "room5", "오늘은 어디 놀러가지??", "08:05:11", 5, 4))
        data.add(Room(6, "", "room6", "오늘 머해?", "1:05:11", 4, 1))
        fgChatAdapter = FgChatAdapter(data)

        rv_chat?.adapter = fgChatAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}