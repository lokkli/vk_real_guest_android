package com.example.petshop.common

import com.example.petshop.network.RetrofitClient
import com.example.petshop.network.RetrofitServices

object Common {
    private const val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}