package com.example.mydictionary.data.repos

import com.example.mydictionary.data.retrofit.SkyengApi
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import io.reactivex.rxjava3.core.Observable

class RepoImpl(private val api: SkyengApi) : Repository {

    override fun getData(word: String): Observable<List<WordEntity>> {
        return api.search(word)
    }
}