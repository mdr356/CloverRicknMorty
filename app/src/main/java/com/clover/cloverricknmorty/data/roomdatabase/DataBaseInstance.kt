package com.clover.cloverricknmorty.data.roomdatabase

import android.content.Context
import androidx.room.Room

object DataBaseInstance {
    fun get_db_instance(applicationContext: Context) =
        CharacterListRoomDatabase.getDatabase(applicationContext).characterDao()
}