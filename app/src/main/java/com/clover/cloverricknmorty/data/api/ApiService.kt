package com.clover.cloverricknmorty.data.api

import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.model.Characters
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * **suspend** -> execute a long running
 * operation and wait for it to complete without blocking
 */
interface ApiService {
    @GET("character")
    suspend fun getCharacters() : Characters

    @GET("location/{id}")
    suspend fun getCharacterLocation(
        @Path("id") id: String,
    ): CharacterLocation
}