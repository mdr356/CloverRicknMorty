package com.clover.cloverricknmorty.di.module

import android.content.Context
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.data.roomdatabase.CharacterListRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun getDatabase(applicationContext: Context): CharacterDao {
        return CharacterListRoomDatabase.getDatabase(applicationContext).characterDao()
    }
}