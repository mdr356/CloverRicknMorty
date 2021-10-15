package com.clover.cloverricknmorty.data.model

/*
No need to use @SerializeName annotation becuase we are using the same variable name as server
*/
data class Characters(
    val info: Info
)
data class Info(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null,
    val result: List<Result>,
)
data class Result(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: Origin,
    val location: Origin? = null,
    val image: String? = null,
    val episode: List<String?>? = null,
    val url: String? = null

)

data class Origin(
    val name: String? = null,
    val url: String? = null,
)
