package com.example.mydictionary.di

import com.example.mydictionary.data.retrofit.SkyengApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL_QUALIFIER = "BASE_URL_QUALIFIER"
private const val SKYENG_API_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

@Module
class RetrofitModule {

    @Singleton
    @Named(BASE_URL_QUALIFIER)
    @Provides
    fun provideBaseUrl(): String = SKYENG_API_BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named(BASE_URL_QUALIFIER) baseUrl: String): Retrofit {
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