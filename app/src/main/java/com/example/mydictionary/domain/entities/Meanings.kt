package com.example.mydictionary.domain.entities

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)
