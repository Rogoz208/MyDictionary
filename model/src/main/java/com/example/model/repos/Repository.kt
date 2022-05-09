package com.example.model.repos

import com.example.model.entities.WordEntity

interface Repository {
    suspend fun getData(word: String): List<WordEntity>
}