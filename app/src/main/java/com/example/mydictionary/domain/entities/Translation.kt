package com.example.mydictionary.domain.entities

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val translation: String?
)
