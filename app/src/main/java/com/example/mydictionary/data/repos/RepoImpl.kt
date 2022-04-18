package com.example.mydictionary.data.repos

import com.example.mydictionary.data.retrofit.SkyengApi
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository

class RepoImpl(private val api: SkyengApi) : Repository {

    override suspend fun getData(word: String): List<WordEntity> {
        return api.searchAsync(word)
    }
}