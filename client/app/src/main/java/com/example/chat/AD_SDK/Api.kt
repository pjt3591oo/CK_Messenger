package com.example.chat.AD_SDK

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.*

interface AudienceInterface {
    @POST("/")
    @FormUrlEncoded
    fun save(
        @Field("adid") adid: String,
        @Field("type") type: String,
        @Field("payload") payload: Any
    ): Call<Any>
}


object API {
    // 모바일에서 localhost, 127.0.0.1은 모바일 자신의 아이피이므로 서버로접속되지 않는다. 가상 에뮬레이터도 별도의 아이피 할당받음
    private var domain: String = "http://192.168.1.136:3000/"
    private var API_KEY: String = ""
    fun AudienceService(): AudienceInterface = retrofit.create(AudienceInterface::class.java)

    fun setApiKey(_API_KEY: String) {
        API_KEY = _API_KEY
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(domain)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}