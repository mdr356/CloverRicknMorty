package com.clover.cloverricknmorty.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.NonCancellable.isActive
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel(){


    // Job instance
    private var job = Job()

    fun getAllCharacters(refreshApiCall: Boolean = false, searchName: String) : LiveData<Resource<List<CharacterList>?>> {
        job.cancel()
        job = Job()

        return liveData(CoroutineScope(job + Dispatchers.IO).coroutineContext) {
            kotlinx.coroutines.delay(300)
            Timber.i("Search for: ")
            Timber.i(searchName)
            emit(Resource.loading(null)) // loading
            if (isDatabaseEmpty() || refreshApiCall) {
                Timber.d("API call to Request Characters")
                try {
                    val apiData = mainRepository.getAllCharacters(searchName)
                    Timber.i(apiData.toString())
                    if (searchName.isEmpty()) insertDataInDatabase(apiData!!)
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

    }

    private fun isDatabaseEmpty(): Boolean = mainRepository.isDatabaseEmpty()


    private suspend fun deleteDatabase() = mainRepository.deleteDatabase()

    private suspend fun insertDataInDatabase(apiData: List<CharacterList>) {
        deleteDatabase()
        mainRepository.insertCharacters(apiData)
    }

}