package com.clover.cloverricknmorty.ui.details.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.util.regex.Pattern

class DetailsViewModel(
    private val mainRepository: MainRepository,
    private val context: Application
) : AndroidViewModel(context) {

    fun getCharacterById(id: Int) = liveData(Dispatchers.Default) {
        emit(mainRepository.getCharacterById(id, context))
    }

    fun getCharacterLocation(url: String) = liveData(Dispatchers.IO) {
        val arr = Pattern.compile("/").split(url)
        val id = arr[arr.size-1]

        try {
            Timber.d("API call to get Characters Location")
            emit(Resource.success(data = mainRepository.getCharacterLocation(id)))
        } catch (ioException: Exception) {
            Timber.e("API call error.")
            emit(
                Resource.error(
                    data = null,
                    message = ioException.message ?: "Error calling getting Characters locaction api"
                )
            )
        }
    }
}