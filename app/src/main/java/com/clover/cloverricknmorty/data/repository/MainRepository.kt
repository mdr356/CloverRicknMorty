package com.clover.cloverricknmorty.data.repository

import android.content.Context
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.data.roomdatabase.DataBaseInstance
import timber.log.Timber
import javax.inject.Inject

interface MainRepository {
    suspend fun getCharacters() : List<CharacterList>?
    suspend fun getCharacterLocation(id: String): CharacterLocation?
    suspend fun insertCharacters(applicationContext: Context, data : List<CharacterList> )
    suspend fun deleteDatabase(applicationContext: Context)
    fun loadCharactersFromDatabase(applicationContext: Context) : List<CharacterList>?
    fun getCharacterById(id: Int, applicationContext: Context): CharacterList
    fun loadCharacter_DB(applicationContext: Context) : CharacterDao?
}

class MainRepositoryImp @Inject constructor(val apiService: ApiService) : MainRepository {

    override suspend fun getCharacters() : List<CharacterList>? {
        return apiService.getCharacters().results
    }

    override suspend fun getCharacterLocation(id: String): CharacterLocation? {
        return apiService.getCharacterLocation(id)
    }

    override suspend fun insertCharacters(applicationContext: Context, data : List<CharacterList> ) {
        for (item in data) {
            loadCharacter_DB(applicationContext).insert(item)
        }
    }

    override suspend fun deleteDatabase(applicationContext: Context) {
        loadCharacter_DB(applicationContext).deleteAll()
    }

    override fun loadCharactersFromDatabase(applicationContext: Context) : List<CharacterList>? {
        Timber.i("Loading data from database")
        return loadCharacter_DB(applicationContext).getCharacters()
    }
    override fun getCharacterById(id: Int, applicationContext: Context): CharacterList {
        return loadCharacter_DB(applicationContext).loadCharacterById(id)
    }


    override fun loadCharacter_DB(applicationContext: Context): CharacterDao {
        return DataBaseInstance.get_db_instance(applicationContext)
    }

}