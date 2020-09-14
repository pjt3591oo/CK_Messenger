package com.example.chat

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import kotlinx.android.synthetic.main.activity_my_profile.*


class MyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_profile)

        val actionBar: ActionBar? = supportActionBar
//        actionBar?.setHomeButtonEnabled(false)
//        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.hide()

        tv_title_profile.text = intent.extras?.getString("roomName")
        tv_msg_profile.text = intent.extras?.getString("userStatusMsg")
        Glide
            .with(iv_profile.context)
            .load(intent.extras?.getString("profileImg"))
            .transform(CenterCrop(), RoundedCorners(200))
            .into(iv_profile)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_static );

        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_static );

        go_home.setOnClickListener {
            onBackPressed()
        }

        tv_move_chat_from_my_profile.setOnClickListener {
            Toast.makeText(this, "나와의 채팅은 봇과 채팅 기능이 들어갈 예정입니다.", Toast.LENGTH_LONG).show()
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