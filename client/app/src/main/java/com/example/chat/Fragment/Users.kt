package com.example.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.API.APIService
import com.example.chat.API.Friend

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Users: Fragment() {
    var fgUserAdapter: FgUserAdapter? = null
    var rv_friend: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fg_user, container, false)

        rv_friend = view.findViewById(R.id.rv_friend);
        rv_friend?.setHasFixedSize(true)
        rv_friend?.layoutManager = LinearLayoutManager(activity)

        var data: ArrayList<Friend> = ArrayList<Friend>()
//
//        data.add(Friend(1, "2020-09-09", 1, 1, 2, FriendDetail("email1", "birth", "m", "f")))
//        data.add(Friend(2, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "f")))
//        data.add(Friend(2, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "f")))
//        data.add(Friend(2, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "f")))
//        data.add(Friend(2, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "f")))

        fgUserAdapter = FgUserAdapter(data)
        rv_friend?.adapter =fgUserAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        APIService.getFriendService().getFriends().enqueue(object: Callback<ArrayList<Friend>> {
            override fun onFailure(call: Call<ArrayList<Friend>>, t: Throwable) {
                Log.i("[SERVER CALL AFTER E]", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Friend>>, res: Response<ArrayList<Friend>>) {
                Log.i("[SERVER CALL AFTER S]", "서버통신 후")
                if (res.isSuccessful) {
                    val resData = res.body()
                    resData?.let {

                        rv_friend?.adapter = FgUserAdapter(it)
                        fgUserAdapter?.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}