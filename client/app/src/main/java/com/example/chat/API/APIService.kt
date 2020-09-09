package com.example.chat.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    fun getFriendService(): FriendInterface = retrofit.create(FriendInterface::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}