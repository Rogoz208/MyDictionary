package com.example.mydictionary.di

import com.example.mydictionary.data.retrofit.SkyengApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Named("baseUrl")
    @Provides
    fun provideBaseUrl(): String = "https://dictionary.skyeng.ru/api/public/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSkyengApi(retrofit: Retrofit): SkyengApi = retrofit.create(SkyengApi::class.java)
}