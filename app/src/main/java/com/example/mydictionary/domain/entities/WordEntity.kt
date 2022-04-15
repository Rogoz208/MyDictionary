package com.example.mydictionary.domain.entities

import com.google.gson.annotations.SerializedName

data class WordEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meanings>?
)