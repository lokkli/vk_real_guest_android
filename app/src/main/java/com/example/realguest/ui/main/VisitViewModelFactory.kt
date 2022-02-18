package com.example.realguest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realguest.network.RetrofitServices

class VisitViewModelFactory(private val apiService: RetrofitServices) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}