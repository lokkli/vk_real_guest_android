package com.example.realguest.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.realguest.common.Common.sharedPref
import com.example.realguest.model.Visit

class VisitSource(private val _retrofitServices: RetrofitServices) : PagingSource<Int, Visit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Visit> {
        try {
            val currentLoadingPageKey = params.key ?: 0
            val response = _retrofitServices
                .getVisitsNew(
                    "Bearer " + sharedPref.getString("access_token", ""),
                    currentLoadingPageKey)

            val responseData = mutableListOf<Visit>()
            val data = response.content
            responseData.addAll(data)
            val nextKey = if (response.last) null else currentLoadingPageKey.plus(1)
            return LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Visit>): Int? {
        TODO("Not yet implemented")
    }
}