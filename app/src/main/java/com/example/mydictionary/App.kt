package com.example.mydictionary

import android.app.Application
import com.example.mydictionary.Di.reposModule
import com.example.mydictionary.Di.retrofitModule
import com.example.mydictionary.Di.viewModelsModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            modules(reposModule, retrofitModule, viewModelsModule)
        }
    }
}