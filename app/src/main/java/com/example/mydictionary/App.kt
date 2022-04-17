package com.example.mydictionary

import android.app.Application
import android.content.Context
import com.example.mydictionary.data.retrofit.RepoImpl
import com.example.mydictionary.domain.repos.Repository

class App : Application() {

    val repo: Repository by lazy { RepoImpl() }
}

val Context.app: App
    get() = applicationContext as App