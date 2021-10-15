package com.clover.cloverricknmorty.data.repository

import com.clover.cloverricknmorty.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getCharacters() = apiHelper.getCharacters()
}