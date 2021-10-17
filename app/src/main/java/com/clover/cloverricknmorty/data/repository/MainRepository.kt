package com.clover.cloverricknmorty.data.repository

import android.content.Context
import com.clover.cloverricknmorty.data.api.ApiHelper
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.roomdatabase.DataBaseInstance
import timber.log.Timber

class MainRepository(private val apiHelper: ApiHelper? = null) {

    suspend fun getCharacters() : List<CharacterList>? {
        return apiHelper?.getCharacters()?.results
    }

    suspend fun getCharacterLocation(id: String): CharacterLocation? {
        return apiHelper?.getCharacterLocations(id)
    }

    suspend fun insertCharacters(applicationContext: Context, data : List<CharacterList> ) {
        for (item in data) {
            loadCharacter_DB(applicationContext).insert(item)
        }
    }

    suspend fun deleteDatabase(applicationContext: Context) {
        loadCharacter_DB(applicationContext).deleteAll()
    }

    fun loadCharactersFromDatabase(applicationContext: Context) : List<CharacterList> {
        Timber.i("Loading data from database")
        return loadCharacter_DB(applicationContext).getCharacters()
    }
    fun getCharacterById(id: Int, applicationContext: Context): CharacterList {
        return loadCharacter_DB(applicationContext).loadCharacterById(id)
    }


    fun loadCharacter_DB(applicationContext: Context) =
        DataBaseInstance.get_db_instance(applicationContext)

}