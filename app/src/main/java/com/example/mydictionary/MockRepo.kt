package com.example.mydictionary

import com.example.mydictionary.domain.entities.Meanings
import com.example.mydictionary.domain.entities.Translation
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import io.reactivex.rxjava3.core.Observable

class MockRepo : Repository {
    private val cache: MutableList<WordEntity> = mutableListOf()

    init {
        fillRepoByTestValues()
    }

    override fun getData(word: String): Observable<List<WordEntity>> {
        return Observable.just(cache)
    }

    private fun fillRepoByTestValues() {
        for (i in 0..19) {
            cache.add(
                i,
                WordEntity(
                    i,
                    "test word $i",
                    listOf(Meanings(Translation("test translation"), null))
                )
            )
        }
    }
}