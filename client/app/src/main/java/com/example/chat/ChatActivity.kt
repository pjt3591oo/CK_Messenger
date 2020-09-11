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