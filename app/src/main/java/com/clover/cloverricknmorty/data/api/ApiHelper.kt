package com.clover.cloverricknmorty.data.api

/*
 * this class will make the API call.
 * using **suspend** -> execute a long running operation.
 */
class ApiHelper(val apiService: ApiService) {
    suspend fun getCharacters() = apiService.getCharacters()

    suspend fun getCharacterLocations(id: String) = apiService.getCharacterLocation(id)
}
