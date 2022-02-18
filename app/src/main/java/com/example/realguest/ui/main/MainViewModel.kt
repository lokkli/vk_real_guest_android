package com.example.realguest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.realguest.network.RetrofitServices
import com.example.realguest.network.VisitSource

class MainViewModel(private val retrofitServices: RetrofitServices) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 2)) {
        VisitSource(retrofitServices)
    }.flow.cachedIn(viewModelScope)
}