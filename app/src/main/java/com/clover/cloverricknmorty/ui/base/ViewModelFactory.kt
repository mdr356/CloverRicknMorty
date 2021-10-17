package com.clover.cloverricknmorty.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clover.cloverricknmorty.app.MyApplication
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper? = null,
    private val applicatinContext: MyApplication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return apiHelper?.let { MainRepository(it) }?.let { MainViewModel(it, applicatinContext) } as T
        } else if(modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return apiHelper?.let { MainRepository(it) }?.let { DetailsViewModel(it, applicatinContext) } as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
