package com.example.chat.API

data class Friends (
    val friends: List<Friend>
)

data class Friend (

    val id: Int,
    val created: String,
    val stats: Int,
    val user_id: Int,
    val friend_id: Int,
    val friend: FreindDetail
)

data class FreindDetail (
    val email: String,
    val birth: String,
    val gender: String,
    val profile_img: String
)