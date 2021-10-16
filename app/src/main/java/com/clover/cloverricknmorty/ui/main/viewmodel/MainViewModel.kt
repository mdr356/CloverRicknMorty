package com.clover.cloverricknmorty.ui.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.lang.Exception

class MainViewModel(
    private val mainRepository: MainRepository,
    private val context: Application
): AndroidViewModel(context){

    // using coroutine to take care of long running operation
    //network thread -> Dispatchers.IO
    fun getCharacters(refreshApiCall: Boolean = false) = liveData(Dispatchers.IO) {
        if (isDatabaseEmpty() || refreshApiCall) {
            Timber.d("API call to Request Characters")
            emit(Resource.loading(null)) // loading
            try {
                val apiData = mainRepository.getCharacters()
                Timber.i(apiData.toString())
                insertDataInDatabase(apiData)
                emit(Resource.success(data = apiData))
            } catch (ioException: Exception) {
                Timber.e("API call error.")
                emit(
                    Resource.error(
                        data = null,
                        message = ioException.message ?: "Error calling getCharacter() api"
                    )
                )
            }
        } else {
            emit(Resource.success(data = mainRepository.loadCharactersFromDatabase(context)))
        }
    }

    fun isDatabaseEmpty() = mainRepository.loadCharacter_DB(context).getCharacters().isEmpty()

    suspend fun deleteDatabase() = mainRepository.deleteDatabase(context)

    suspend fun insertDataInDatabase(apiData: List<CharacterList>) {
        deleteDatabase()
        mainRepository.insertCharacters(context, apiData)
    }

}