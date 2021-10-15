package com.clover.cloverricknmorty.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository): ViewModel(){

    // using coroutine to take care of long running operation
    //network thread -> Dispatchers.IO
    fun getCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null)) // loading
        try {
            val apiData = mainRepository.getCharacters();
            emit(Resource.success(data = apiData))
        } catch (ioException: Exception) {
            emit(Resource.error(data = null, message = ioException.message ?: "Error calling getCharacter() api"))
        }
    }
}