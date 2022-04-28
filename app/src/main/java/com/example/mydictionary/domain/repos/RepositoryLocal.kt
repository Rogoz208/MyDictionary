package com.example.mydictionary.domain.repos

import com.example.mydictionary.domain.entities.WordEntity

interface RepositoryLocal : Repository {

    suspend fun saveToDb(word: WordEntity)
}