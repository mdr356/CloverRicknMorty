package com.clover.cloverricknmorty.data.api

import com.clover.cloverricknmorty.data.model.Characters
/*
 * **suspend** -> execute a long running
 * operation and wait for it to complete without blocking
 */
interface ApiService {
    suspend fun getCharacters() : Characters
}