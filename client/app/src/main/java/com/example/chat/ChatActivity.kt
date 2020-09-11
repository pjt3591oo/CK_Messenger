package com.example.chat

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.API.Msg
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        actionBar?.title = "멍개씌~" // 액션바 타이틀

        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_static );

        // 우측에 튀어나온 메뉴 아이템 클릭시 onNavigationITemSelected 호출하도록 설정
        chat_menu_view.setNavigationItemSelectedListener(this)

        var chats: ArrayList<Msg> = ArrayList<Msg>()

        chats.add(Msg(1, "안녕하세요", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:00:11"))
        chats.add(Msg(2, "반갑습니다", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "09:00:11"))
        chats.add(Msg(3, "반가워요~", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:01:11"))
        chats.add(Msg(4, "오늘 날씨가 좋네요", 1, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "13:00:11"))
        chats.add(Msg(5, "안녕하세요", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:00:11"))
        chats.add(Msg(6, "반갑습니다", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "09:00:11"))
        chats.add(Msg(7, "반가워요~", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:01:11"))
        chats.add(Msg(8, "오늘 날씨가 좋네요", 1, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "13:00:11"))
        chats.add(Msg(9, "안녕하세요", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:00:11"))
        chats.add(Msg(10, "반갑습니다", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "09:00:11"))
        chats.add(Msg(11, "반가워요~", 2, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",1, "17:01:11"))
        chats.add(Msg(12, "오늘 날씨가 좋네요", 1, "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo",0, "13:00:11"))

        rv_chats.setHasFixedSize(true)
        rv_chats.layoutManager = LinearLayoutManager(this)
        rv_chats.adapter = ChatAdapter(chats)
    }

    override fun finish() {
        super.finish()
        closeAnimation()
    }

    // actionbar 뒤로가기 버튼 클릭
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // 액션바에 액션 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_chat, menu)
        return true
    }

    // 액션바 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_menu -> {
                Log.d("[EVENT] actionbar", "click menu item by actionbar from ChatActivity")
                Log.d("[EVENT] actionbar", (chat_menu_layout_drawer.isDrawerOpen(GravityCompat.END)).toString())
                if(!chat_menu_layout_drawer.isDrawerOpen(GravityCompat.END)) {
                    chat_menu_layout_drawer.openDrawer(GravityCompat.END)
                } else {
                    chat_menu_layout_drawer.closeDrawers()
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 우측에 튀어나온 메뉴 아이템 이벤트 리스너
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        Toast.makeText( applicationContext, "Clicked: ${p0} ", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun closeAnimation() {
        overridePendingTransition( R.anim.slide_out_static, R.anim.slide_out_bottom );
    }

    override fun onBackPressed() {
        if (chat_menu_layout_drawer.isDrawerOpen(GravityCompat.END)) {
            chat_menu_layout_drawer.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}