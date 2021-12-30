package com.example.petshop.network

import com.example.petshop.model.Movie
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}