package com.clover.cloverricknmorty.data.roomdatabase

import androidx.room.*
import com.clover.cloverricknmorty.data.model.CharacterList

/*
 * Data access object. A mapping of SQL queries to functions
 */
@Dao
interface CharacterDao {
    @Query("SELECT * FROM characterlist")
    fun getCharacters(): List<CharacterList>?

    @Query("SELECT * FROM characterlist WHERE id == (:id)")
    fun loadCharacterById(id: Int): CharacterList

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterList: CharacterList)

   /* @Insert
    suspend fun insertAll(vararg characters: List<CharacterList>)*/

    @Query("DELETE FROM characterlist")
    suspend fun deleteAll()
}

