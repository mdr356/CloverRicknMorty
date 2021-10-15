package com.clover.cloverricknmorty.data.api

import com.clover.cloverricknmorty.data.model.Characters

interface ApiService {
    // **suspend** -> execute a long running operation and wait for it to complete without blocking
    suspend fun getCharacters() : Characters
}