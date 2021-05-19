package com.example.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.API.Msg

import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.IOException

import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException

class ChatActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var originHeight = -1

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        actionBar?.title = intent.extras?.getString("roomName") // 액션바 타이틀

        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_static);

        // 우측에 튀어나온 메뉴 아이템 클릭시 onNavigationITemSelected 호출하도록 설정
        chat_menu_view.setNavigationItemSelectedListener(this)

        var chats: ArrayList<Msg> = ArrayList<Msg>()

        val texsImg:String = "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo"
        val senderImg: String = "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo"

        chats.add(Msg(1, texsImg, "img", 2, senderImg, 1, "17:00:11"))
        chats.add(Msg(2, texsImg, "img", 1, senderImg, 0, "09:00:11"))
        chats.add(Msg(3, "반가워요~", "text", 2, senderImg, 1, "17:01:11"))
        chats.add(Msg(4, texsImg, "img", 1, senderImg, 0, "13:00:11"))
        chats.add(Msg(5, "안녕하세요", "text", 2, senderImg, 1, "17:00:11"))
        chats.add(Msg(6, texsImg, "img", 2, senderImg, 0, "09:00:11"))
        chats.add(Msg(7, "반가워요~", "text", 2, senderImg, 1, "17:01:11"))
        chats.add(Msg(8, "오늘 날씨가 좋네요", "text", 2, senderImg, 0, "13:00:11"))
        chats.add(Msg(9, "안녕하세요", "text", 2, senderImg, 1, "17:00:11"))
        chats.add(Msg(10, "반갑습니다", "text", 2, senderImg, 0, "09:00:11"))
        chats.add(Msg(11, "반가워요~", "text", 2, senderImg, 1, "17:01:11"))
        chats.add(Msg(12, "오늘 날씨가 좋네요", "text", 1, senderImg, 0, "13:00:11"))

        rv_chats.layoutManager = LinearLayoutManager(this)
        var chatAdapter = ChatAdapter(chats, this)
        rv_chats.adapter = chatAdapter

        rv_chats.smoothScrollToPosition(chatAdapter.itemCount)

        // 전송버튼 클릭
        iv_send_chat.setOnClickListener {
            val msg: String = et_msg_for_send.text.toString()
            Log.d("[EVENT] CLICK: ", msg)

            if (msg != "") {
                chats.add(Msg(12, msg, "text", 1, senderImg, 0, "13:00:11"))
                chatAdapter.notifyItemInserted(chatAdapter.itemCount)

                rv_chats.smoothScrollToPosition(chatAdapter.itemCount)
                et_msg_for_send.setText("")

                Log.d("[EVENT] CLICK: ", msg)

                ADID_Task(chats, chatAdapter)
            }
        }

        // 키보드가 올라가면서 뷰가 전체적으로 밀리면서 recyclerView의 레이아웃 크키변경됨 => 이떄 뷰 스크롤을 가장 아래로 내린다
        rv_chats.addOnLayoutChangeListener(View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (bottom < oldBottom) {
                rv_chats.postDelayed(Runnable {
                    rv_chats.smoothScrollToPosition(
                        chatAdapter.itemCount - 1
                    )
                }, 100)
            }
        })

        addBtnClick()
        editClick()
    }

    private fun ADID_Task(chats: ArrayList<Msg>, chatAdapter: ChatAdapter) {
        // TODO: AsyncTask 대체를 머로 해야되지??.....
        val task: AsyncTask<Void?, Void?, String?> = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void?, Void?, String?>() {
            override fun onPostExecute(adid: String?) {
                val senderImg: String = "https://lh3.googleusercontent.com/ogw/ADGmqu_KF5ZFGmysQOIgIfY5ZolLw21UFOgJgP7Euk3j=s32-c-mo"
                val msg: String = adid.toString()

                chats.add(Msg(12, msg, "text", 2, senderImg, 0, "13:00:11"))
                chatAdapter.notifyItemInserted(chatAdapter.itemCount)

                rv_chats.smoothScrollToPosition(chatAdapter.itemCount)
            }

            override fun doInBackground(vararg p0: Void?): String? {
                val adid: String = _getAdid(applicationContext)
                Log.d("[EVENT] ADID", adid)
                return adid
            }
        }
        task.execute()
    }

    fun _getAdid(context: android.content.Context): String {
        var adInfo: AdvertisingIdClient.Info? = null

        try {
            adInfo = AdvertisingIdClient.getAdvertisingIdInfo(applicationContext)
        } catch (e: IOException) {

        } catch (e: GooglePlayServicesNotAvailableException) {

        }
        val id = adInfo!!.id
        val isLAT = adInfo!!.isLimitAdTrackingEnabled

        return id
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
                if (!chat_menu_layout_drawer.isDrawerOpen(GravityCompat.END)) {
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
        Toast.makeText(applicationContext, "Clicked: $p0 ", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun addBtnClick () {

        iv_etcs_send.setOnClickListener {
            if (isKeyboardShow() || v_emoji.visibility == View.GONE) {
                hideKeyboard(et_msg_for_send)
                v_emoji.visibility = View.VISIBLE
                et_msg_for_send.requestFocus()
            } else {
                v_emoji.visibility = View.GONE
            }
        }
    }

    private fun editClick () {
        et_msg_for_send.setOnClickListener {
            this?.window?.setSoftInputMode(SOFT_INPUT_ADJUST_PAN)
            v_emoji.visibility = View.VISIBLE
        }

        et_msg_for_send.setOnKeyListener { v, keyCode, event ->
            Log.d("test", "tesd13234")

            true
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun showKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 1)
    }

    private fun isKeyboardShow(): Boolean {
        val visibleBounds = Rect()
        layout_root_chat.getWindowVisibleDisplayFrame(visibleBounds)
        val heightDiff = layout_root_chat.height - visibleBounds.height()
        return heightDiff > 0
    }

    private fun closeAnimation() {
        overridePendingTransition(R.anim.slide_out_static, R.anim.slide_out_bottom);
    }

    override fun onBackPressed() {
        Log.d("test", "test")

        if (chat_menu_layout_drawer.isDrawerOpen(GravityCompat.END)) {
            chat_menu_layout_drawer.closeDrawers()
            return
        }  else if (v_emoji.visibility == View.VISIBLE){
            v_emoji.visibility = View.GONE
            return
        } else {
            super.onBackPressed()
            return
        }
    }

    fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("test", "123")
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // Eat the event
            return true
        }
        return false
    }
}