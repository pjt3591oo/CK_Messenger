package com.example.chat.AD_SDK

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.io.IOException

import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// [0] 컨텍스트 전달
// [1] API 호출을 위한 키값 전달
class ADID<T>(var applicationContext: android.content.Context, var apiKey: String) : AsyncTask<Any, String, Payload<T>>() {
    val api: API = API

    // execute 호출시 실행
    // [0]: String = type      타입 또는 행위
    // [1]: Data   = payload   수집 데이터
    override fun doInBackground(vararg parameters: Any): Payload<T>? {
        val adid: String = _getAdid()
        val type: String = parameters[0] as String
        val payload: T = parameters[1] as T

        if (parameters.size < 2) {
            throw IllegalArgumentException("execute 메서드는 2개(type, payload)의 인자가 필요합니다.")
        }

        return Payload(adid, type, payload)
    }

    protected override fun onProgressUpdate(vararg progress: String) { }

    override fun onPostExecute(result: Payload<T>) {
        // ADID와 데이터 전송
        val adid: String = result.adid
        val type: String = result.type
        val payload: T? = result?.payload

        // Logging
        Log.d("[SDK-ADID] adid: ", adid)
        Log.d("[SDK-ADID] type: ", type)
        if (payload != null) {
            Log.d("[SDK-ADID] payload: ", payload.toString())
        }

        api.setApiKey(apiKey)

        if (payload != null) {

            api.AudienceService().save(adid, type, toJson(payload)).enqueue(object: Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.e("[API] error", t.toString())
                }

                override fun onResponse(call: Call<Any>, res: Response<Any>) {
                    Log.d("[API] success", res.toString())
                }
            })
        }
    }

    fun _getAdid(): String {
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

    fun toJson(payload: T): String {
        var gson: Gson = GsonBuilder().create()
        return gson.toJson(payload)
    }
}