package com.example.model.repos

import com.example.model.entities.WordEntity

interface RepositoryLocal : Repository {

    suspend fun saveToDb(word: WordEntity)
    suspend fun searchByWord(word: String): WordEntity?
}