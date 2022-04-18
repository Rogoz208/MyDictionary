package com.example.mydictionary.data.retrofit

import com.example.mydictionary.domain.entities.WordEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {

    @GET("words/search")
    suspend fun searchAsync(@Query("search") wordToSearch: String): List<WordEntity>
}