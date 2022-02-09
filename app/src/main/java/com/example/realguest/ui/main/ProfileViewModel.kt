package com.example.realguest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realguest.model.Profile
import com.example.realguest.repository.ProfileRepository

class ProfileViewModel : ViewModel() {
    private val profileData = MutableLiveData<Profile>()

    fun loadData(): MutableLiveData<Profile?> {
        return ProfileRepository(profileData).get()
    }
}