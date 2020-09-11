package com.example.chat.API

data class Msg(
    val id: Int,
    val msg: String,
    // text, img, etc
    // val msgType: String,
    val sender: Int,

    val senderImg: String,

    val readCnt: Int,
    val dt: String
)