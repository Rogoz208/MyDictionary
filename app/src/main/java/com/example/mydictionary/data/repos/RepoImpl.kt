package com.example.mydictionary.data.repos

import com.example.model.entities.WordEntity
import com.example.model.repos.Repository
import com.example.retrofit.retrofit.SkyengApi

class RepoImpl(private val api: SkyengApi) : Repository {

    override suspend fun getData(word: String): List<WordEntity> {
        return api.searchAsync(word)
    }
}