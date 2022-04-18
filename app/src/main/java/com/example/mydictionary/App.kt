package com.example.mydictionary

import android.app.Application
import android.content.Context
import com.example.mydictionary.di.AppComponent
import com.example.mydictionary.di.DaggerAppComponent

class App : Application() {

    val di: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App