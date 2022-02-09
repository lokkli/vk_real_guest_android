package com.example.realguest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realguest.common.Common
import com.example.realguest.common.Common.retrofitService
import com.example.realguest.model.Visits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _visits: MutableLiveData<Visits> by lazy {
        MutableLiveData<Visits>().also {
            loadVisits()
        }
    }

    fun getVisits() = _visits

    private fun loadVisits() {
        // Do an asynchronous operation to fetch users.
        retrofitService.getVisits(
            "Bearer " + Common.sharedPref.getString("access_token", "")!!
        )
            .enqueue(object : Callback<Visits> {
                override fun onFailure(call: Call<Visits>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Visits>, response: Response<Visits>) {
                    if (response.isSuccessful) {
                        _visits.value = response.body()!!
                    }
                }
            })
    }
}