package com.clover.cloverricknmorty.data.model

/*
No need to use @SerializeName annotation becuase we are using the same variable name as server
*/
data class Characters(
    val info: CharactersInfo
)
data class CharactersInfo(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null,
    val result: List<CharacterList>,
)
data class CharacterList(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: CharactersOrigin,
    val location: CharactersOrigin? = null,
    val image: String? = null,
    val episode: List<String?>? = null,
    val url: String? = null

)

data class CharactersOrigin(
    val name: String? = null,
    val url: String? = null,
)
