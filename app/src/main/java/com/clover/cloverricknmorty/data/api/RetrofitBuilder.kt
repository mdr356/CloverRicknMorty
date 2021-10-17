package com.clover.cloverricknmorty.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// this is a singleton
/*
object RetrofitBuilder {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}*/
