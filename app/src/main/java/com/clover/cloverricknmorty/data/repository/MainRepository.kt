package com.clover.cloverricknmorty.data.repository

import android.content.Context
import com.clover.cloverricknmorty.data.api.ApiHelper
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.roomdatabase.DataBaseInstance
import timber.log.Timber

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCharacters() : List<CharacterList>  {
        Timber.i("Add data to database")
        return apiHelper.getCharacters().results
    }

    fun loadCharactersFromDatabase(applicationContext: Context) : List<CharacterList> {
        Timber.i("Loading data from database")
        return loadCharacter_DB(applicationContext).getCharacters()
    }
    suspend fun insertCharacters(applicationContext: Context, data : List<CharacterList> ) {
        for (item in data) {
            loadCharacter_DB(applicationContext).insert(item)
        }
    }

    suspend fun deleteDatabase(applicationContext: Context) {
        loadCharacter_DB(applicationContext).deleteAll()
    }

    fun loadCharacter_DB(applicationContext: Context) =
        DataBaseInstance.get_db_instance(applicationContext)
}