package com.clover.cloverricknmorty.data.api

import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.model.Characters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
 * **suspend** -> execute a long running
 * operation and wait for it to complete without blocking
 */
interface ApiService {
    @GET("character/")
    suspend fun getAllCharacters(
        @Query("name") searchName: String) : Characters

    @GET("location/{id}")
    suspend fun getCharacterLocation(
        @Path("id") id: String,
    ): CharacterLocation

}