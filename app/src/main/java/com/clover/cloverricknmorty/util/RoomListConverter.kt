package com.clover.cloverricknmorty.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomListConverter {

    /*
     * convert to json
     */
    @TypeConverter
    fun fromListJson(arg: List<String?>?): String? {
        return Gson().toJson(arg)

    }

    /*
     * convert to List<String>
     */

    @TypeConverter
    fun toListJson(argString: String): List<String>? {
        val type = object: TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(argString, type)
    }
}