package com.clover.cloverricknmorty.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clover.cloverricknmorty.data.model.CharacterList


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(CharacterList::class), version = 1, exportSchema = false)
public abstract class CharacterListRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CharacterListRoomDatabase? = null

        fun getDatabase(context: Context): CharacterListRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterListRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
