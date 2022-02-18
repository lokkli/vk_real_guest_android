package com.example.realguest.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.realguest.MyApplication.Companion.applicationContext
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val okhttpClient = OkHttpClient.Builder()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(
                    okhttpClient.apply {
                        addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
                        connectTimeout(15, TimeUnit.SECONDS)
                        addInterceptor(ChuckerInterceptor(applicationContext()))
                    }.build()
                ).build()
        }
        return retrofit!!
    }
}