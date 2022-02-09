package com.example.realguest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val okhttpClient = OkHttpClient.Builder()

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
      //      val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:SSSSSSS").create()
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                //    .addConverterFactory(GsonConverterFactory.create(gson))
                .client(
                    okhttpClient.apply {
                        okhttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
                        connectTimeout(15, TimeUnit.SECONDS)
                    }.build()
                )
                .build()
        }
        return retrofit!!
    }
}