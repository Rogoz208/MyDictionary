package com.example.mydictionary.data.retrofit

import com.example.mydictionary.domain.entities.WordEntity
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<WordEntity>>
}