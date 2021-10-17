package com.clover.cloverricknmorty.data.model

import androidx.room.*
import com.clover.cloverricknmorty.util.RoomCharactersOriginConverter
import com.clover.cloverricknmorty.util.RoomListConverter

/*
No need to use @SerializeName annotation becuase we are using the same variable name as server
*/

data class Characters(
    val info: CharactersInfo,
    val results: List<CharacterList>,
)
data class CharactersInfo(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null,
)
@Entity(primaryKeys = ["id"])
data class CharacterList(
    var id: Int = 0,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,
    @TypeConverters(RoomCharactersOriginConverter::class)
    var origin: CharactersOrigin? = null,
    @TypeConverters(RoomCharactersOriginConverter::class)
    var location: CharactersOrigin? = null,
    var image: String? = null,
    @TypeConverters(RoomListConverter::class)
    var episode: List<String?>? = null,
    var url: String? = null

)

data class CharactersOrigin(
    val name: String? = null,
    val url: String? = null,
) {
    constructor(): this("","")
}
