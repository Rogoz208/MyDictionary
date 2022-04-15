package com.example.mydictionary.domain.repos

import com.example.mydictionary.domain.entities.WordEntity
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getData(word: String): Observable<List<WordEntity>>
}