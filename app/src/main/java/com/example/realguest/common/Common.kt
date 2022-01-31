package com.example.realguest.common

import android.content.SharedPreferences
import com.example.realguest.network.RetrofitClient
import com.example.realguest.network.RetrofitServices

object Common {
    private const val BASE_URL =
        "http://vkrealguests-env.eba-j3nabdai.eu-central-1.elasticbeanstalk.com"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

    lateinit var sharedPref : SharedPreferences
}