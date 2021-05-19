package com.example.chat.AD_SDK

class Payload<T>(
    var adid: String,
    var type: String,
    var payload: T?
) { }