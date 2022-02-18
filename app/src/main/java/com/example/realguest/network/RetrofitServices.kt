package com.example.realguest.network

import com.example.realguest.model.Visits
import com.example.realguest.model.Auth
import com.example.realguest.model.Profile
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServices {
    //http://vkrealguests-env.eba-j3nabdai.eu-central-1.elasticbeanstalk.com/auth?username=89252891511&password=1385624aaA
    @POST("auth")
    fun auth(@Query("username") username: String, @Query("password") password: String): Call<Auth>

    @GET("profile")
    fun getProfile(@Header("Authorization") token: String): Call<Profile>

    @GET("visits")
   suspend fun getVisitsNew(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 0
    ): Visits
}