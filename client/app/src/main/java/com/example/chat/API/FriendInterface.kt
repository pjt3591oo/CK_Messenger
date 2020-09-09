package com.example.chat.API

import retrofit2.Call
import retrofit2.http.*

interface FriendInterface {
//    @Headers("")
    @GET("/friends")
    fun getFriends(
    ): Call<ArrayList<Friend>>

//    @Headers("")
    @POST("/friends")
    @FormUrlEncoded
    fun addFriend(
        @Field("user_id") userId: Int,
        @Field("Friend_id") FriendId: Int
    ): Call<ArrayList<Friend>>
}