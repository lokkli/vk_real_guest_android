package com.example.realguest.repository

import androidx.lifecycle.MutableLiveData
import com.example.realguest.common.Common
import com.example.realguest.common.Common.retrofitService
import com.example.realguest.model.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository(private val liveData: MutableLiveData<Profile>) {
    fun get(): MutableLiveData<Profile?> {
        retrofitService.getProfile("Bearer " + Common.sharedPref.getString("access_token", "")!!)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    liveData.value = response.body()
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    liveData.value = null
                }
            })
        return liveData
    }
}