package com.clover.cloverricknmorty.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel(){

    // using coroutine to take care of long running operation
    //network thread -> Dispatchers.IO
    fun getCharacters(refreshApiCall: Boolean = false) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null)) // loading
        if (isDatabaseEmpty() || refreshApiCall) {
            Timber.d("API call to Request Characters")
            try {
                val apiData = mainRepository.getCharacters()
                Timber.i(apiData.toString())
                insertDataInDatabase(apiData!!)
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
            val data = mainRepository.loadCharactersFromDatabase()
            if (data.isNullOrEmpty()) {
                emit(Resource.error(
                    data = null,
                    message = "Error loading data"
                ))
            } else {
                emit(Resource.success(data = mainRepository.loadCharactersFromDatabase()))
            }
        }
    }

    private fun isDatabaseEmpty(): Boolean = mainRepository.isDatabaseEmpty()


    private suspend fun deleteDatabase() = mainRepository.deleteDatabase()

    private suspend fun insertDataInDatabase(apiData: List<CharacterList>) {
        deleteDatabase()
        mainRepository.insertCharacters(apiData)
    }

}