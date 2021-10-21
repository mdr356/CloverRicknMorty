package com.clover.cloverricknmorty.data.repository

import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import timber.log.Timber
import javax.inject.Inject

interface MainRepository {
    suspend fun getCharacters() : List<CharacterList>?
    suspend fun getCharacterLocation(id: String): CharacterLocation?
    suspend fun insertCharacters(data : List<CharacterList> )
    suspend fun deleteDatabase()
    fun loadCharactersFromDatabase() : List<CharacterList>?
    fun getCharacterById(id: Int): CharacterList
    fun isDatabaseEmpty(): Boolean
}

class MainRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val database: CharacterDao,
) : MainRepository {

    override suspend fun getCharacters() : List<CharacterList>? {
        return apiService.getCharacters().results
    }

    override suspend fun getCharacterLocation(id: String): CharacterLocation? {
        return apiService.getCharacterLocation(id)
    }

    override suspend fun insertCharacters(data : List<CharacterList> ) {
        for (item in data) {
            database.insert(item)
        }
    }

    override suspend fun deleteDatabase() {
        database.deleteAll()
    }

    override fun loadCharactersFromDatabase() : List<CharacterList>? {
        Timber.i("Loading data from database")
        return database.getCharacters()
    }
    override fun getCharacterById(id: Int): CharacterList {
        return database.loadCharacterById(id)
    }

    override fun isDatabaseEmpty(): Boolean {
        return database.getCharacters()?.isEmpty() ?: false
    }
}
