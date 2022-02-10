package com.example.realguest.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realguest.common.Common.retrofitService
import com.example.realguest.common.Common.sharedPref
import com.example.realguest.model.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _profile: MutableLiveData<Profile> by lazy {
        MutableLiveData<Profile>().also {
            loadProfile()
        }
    }

    fun getProfile() = _profile

    private fun loadProfile() {
        retrofitService.getProfile("Bearer " + sharedPref.getString("access_token", "")!!)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) _profile.value = response.body()!!
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}