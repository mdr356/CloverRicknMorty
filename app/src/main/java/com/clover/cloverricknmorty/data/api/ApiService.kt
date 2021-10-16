package com.clover.cloverricknmorty.data.api

import com.clover.cloverricknmorty.data.model.Characters
import retrofit2.http.GET

/*
 * **suspend** -> execute a long running
 * operation and wait for it to complete without blocking
 */
interface ApiService {
    @GET("character")
    suspend fun getCharacters() : Characters
}