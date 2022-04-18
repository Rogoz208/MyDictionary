package com.example.mydictionary.di

import com.example.mydictionary.ui.screens.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, ReposModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}