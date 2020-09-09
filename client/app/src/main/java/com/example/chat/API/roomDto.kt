package com.example.chat.API

import java.util.*

data class Room (
    var id: Int,
    val img: String,
    val name: String,
    val lastMsg: String,
    val datetime: String,
    var joinedCnt: Int,
    var chatCnt: Int
)