package com.example.chat

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.activity_friend_profile.*

class FriendProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_friend_profile)
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

        go_home.setOnClickListener {
            onBackPressed()
        }

        tv_move_chat_from_profile.setOnClickListener {

            var i = Intent(it.context, ChatActivity::class.java)
            i.putExtra("roomName", intent.extras?.getString("roomName") )
            it.context.startActivity(i)
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