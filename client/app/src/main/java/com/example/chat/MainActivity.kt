package com.example.chat

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        menu.showBadge(R.id.bottom_user, 1)
        menu.showBadge(R.id.bottom_chat, 53)
//        menu.showBadge(R.id.bottom_more)

        naviEventHandle()
    }

    // 액션바에 액션 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_actions, menu)
        return true
    }

    // 액션바 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val num:String = item.itemId.toString()
        when(item.itemId) {
            R.id.action_search -> {
                Log.d("action_search", num)
                return true
            }
            R.id.action_new_chat -> {
                Log.d("action_new_chat", num)
                return true
            }
            R.id.action_edit -> {
                Log.d("action_edit", num)
                return true
            }
            R.id.config_menu_align -> {
                Log.d("config_menu_align", num)
                return true
            }
            R.id.config_menu_al_config -> {
                Log.d("config_menu_al_config", num)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun naviEventHandle() {
        menu.setOnItemSelectedListener { id ->
            val ft = supportFragmentManager.beginTransaction()
            when (id) {
                R.id.bottom_user -> {
                    println("bottom_user")
                    ft.replace(R.id.frame_layout, Users()).commit()
                }
                R.id.bottom_chat -> {
                    println("bottom_chat")
                    ft.replace(R.id.frame_layout, Chats()).commit()
                }
                R.id.bottom_news -> {
                    println("bottom_news")
                    ft.replace(R.id.frame_layout, News()).commit()
                }
                R.id.bottom_more -> {
                    println("bottom_more")
                    ft.replace(R.id.frame_layout, Mores()).commit()
                }
            }
        }
    }

    fun fragmentChange() {

    }

}