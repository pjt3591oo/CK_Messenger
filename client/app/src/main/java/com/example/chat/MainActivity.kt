package com.example.chat

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.ActionBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}