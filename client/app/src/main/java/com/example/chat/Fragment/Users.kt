package com.example.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.API.Friend
import com.example.chat.API.FriendDetail


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

        val linearLayoutManager = LinearLayoutManager(activity)
        rv_friend?.layoutManager = LinearLayoutManager(activity)

        val vItemDecoration = DividerItemDecoration(
            rv_friend?.context,
            linearLayoutManager.orientation
        )
        rv_friend?.addItemDecoration(vItemDecoration)

        var data0: ArrayList<Friend> = ArrayList<Friend>()
        var data1: ArrayList<Friend> = ArrayList<Friend>()
        var data2: ArrayList<Friend> = ArrayList<Friend>()
        var data3: ArrayList<Friend> = ArrayList<Friend>()
        var data4: ArrayList<Friend> = ArrayList<Friend>()

        var name: ArrayList<String> = ArrayList<String>()
        var data: ArrayList<ArrayList<Friend>> = ArrayList<ArrayList<Friend>>()

        name.add("내 프로필")
        name.add("생일인 친구")
        name.add("즐겨찾기")
        name.add("채널")
        name.add("친구")

        data0.add(Friend(1, "2020-09-09", 1, 1, 2, FriendDetail("멍선생", "birth", "m", "data:image/png;base64,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","안녕 난 멍선생이야!", "안드~ 안드~")))

        data1.add(Friend(2, "2020-09-09", 1, 1, 2, FriendDetail("email1", "birth", "m", "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo","안녕", "")))

        data2.add(Friend(3, "2020-09-09", 1, 1, 2, FriendDetail("email1", "birth", "m", "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo","안녕", "")))

        data3.add(Friend(4, "2020-09-09", 1, 1, 2, FriendDetail("email1", "birth", "m", "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo","안녕", "")))
        data3.add(Friend(5, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "https://avatars0.githubusercontent.com/u/15213202?s=64&v=4","안녕4", "")))

        data4.add(Friend(6, "2020-09-09", 1, 1, 2, FriendDetail("email1", "birth", "m", "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo","안녕", "")))
        data4.add(Friend(7, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "https://avatars0.githubusercontent.com/u/15213202?s=64&v=4","안녕1", "테스트")))
        data4.add(Friend(8, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo","안녕2", "")))
        data4.add(Friend(9, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "https://avatars0.githubusercontent.com/u/15213202?s=64&v=4","안녕3", "")))
        data4.add(Friend(10, "2020-09-09", 1, 1, 2, FriendDetail("email2", "birth", "m", "https://avatars0.githubusercontent.com/u/15213202?s=64&v=4","안녕4", "")))

        data.add(data0)
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)

        fgUserAdapter = activity?.let { FgUserAdapter(it, data, name) }

        rv_friend?.adapter = fgUserAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        APIService.getFriendService().getFriends().enqueue(object: Callback<ArrayList<Friend>> {
//            override fun onFailure(call: Call<ArrayList<Friend>>, t: Throwable) {
//                Log.i("[SERVER CALL AFTER E]", t.toString())
//            }
//
//            override fun onResponse(call: Call<ArrayList<Friend>>, res: Response<ArrayList<Friend>>) {
//                Log.i("[SERVER CALL AFTER S]", "서버통신 후")
//                if (res.isSuccessful) {
//                    val resData = res.body()
//                    resData?.let {
//
////                        rv_friend?.adapter = FgUserAdapter(it)
////                        fgUserAdapter?.notifyDataSetChanged()
//                    }
//                }
//            }
//        })
    }
}