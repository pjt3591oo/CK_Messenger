package com.example.chat

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_friend_profile.*


class MyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_profile)

        val actionBar: ActionBar? = supportActionBar
//        actionBar?.setHomeButtonEnabled(false)
//        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.hide()

        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_static );

        go_home.setOnClickListener {
            onBackPressed()
        }
    }

    override fun finish() {
        super.finish()
        closeAnimation()
    }

    private fun closeAnimation() {
        overridePendingTransition( R.anim.slide_out_static, R.anim.slide_out_bottom );
    }
}