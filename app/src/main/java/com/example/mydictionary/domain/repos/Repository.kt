package com.example.mydictionary.domain.repos

import com.example.mydictionary.domain.entities.WordEntity
import retrofit2.Call

interface Repository {
    suspend fun getData(word: String): List<WordEntity>
}