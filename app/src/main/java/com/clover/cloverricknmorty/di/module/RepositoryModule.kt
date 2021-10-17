package com.clover.cloverricknmorty.di.module

import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.data.repository.MainRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun bindMainRepository(apiService: ApiService) : MainRepository {
        return MainRepositoryImp(apiService)
    }

    // Add More Repository as needed.
}