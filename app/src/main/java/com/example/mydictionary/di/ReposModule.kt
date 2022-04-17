package com.example.mydictionary.di

import com.example.mydictionary.data.repos.RepoImpl
import com.example.mydictionary.data.retrofit.SkyengApi
import com.example.mydictionary.domain.repos.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Singleton
    @Provides
    fun provideRepo(api: SkyengApi): Repository = RepoImpl(api)
}