package com.clover.cloverricknmorty.util

import androidx.room.TypeConverter
import com.clover.cloverricknmorty.data.model.CharactersOrigin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomCharactersOriginConverter {
    @TypeConverter
    fun fromCharactersOrigin(arg: CharactersOrigin) : String {
        return Gson().toJson(arg)
    }

    @TypeConverter
    fun toCharactersOrigin(arg: String): CharactersOrigin {
        val type = object: TypeToken<CharactersOrigin>() {}.type
        return Gson().fromJson(arg, type)
    }
}