package com.example.hiltpractice.remote

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getAll(): List<BlogNetworkEntity>

}