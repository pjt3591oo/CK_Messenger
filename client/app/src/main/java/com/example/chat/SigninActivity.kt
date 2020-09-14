package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        hideActionBar()

        btn_signin.setOnClickListener {
            moveMainActivity()
        }

    }

    private fun moveMainActivity () {
        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun hideActionBar () {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }
}