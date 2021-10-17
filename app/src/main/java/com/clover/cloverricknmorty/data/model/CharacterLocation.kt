package com.clover.cloverricknmorty.data.model

data class CharacterLocation (
    var id: Int?,
    var name: String?,
    var type: String?,
    var dimension: String?,
    var residents : List<String>?,
    var url: String?,
    var created: String?
)
