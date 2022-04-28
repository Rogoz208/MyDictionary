@file:Suppress("RemoveExplicitTypeArguments")

package com.example.mydictionary

import androidx.room.Room
import com.example.mydictionary.data.db.HistoryDatabase
import com.example.mydictionary.data.db.dao.HistoryDao
import com.example.mydictionary.data.repos.RepoImpl
import com.example.mydictionary.data.repos.RoomRepoImpl
import com.example.mydictionary.data.retrofit.SkyengApi
import com.example.mydictionary.domain.repos.Repository
import com.example.mydictionary.domain.repos.RepositoryLocal
import com.example.mydictionary.ui.screens.history.viewmodel.HistoryActivityViewModel
import com.example.mydictionary.ui.screens.main.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_QUALIFIER = "BASE_URL_QUALIFIER"
private const val SKYENG_API_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

object Di {

    val reposModule = module {

        single<Repository> { RepoImpl(api = get()) }
        single<RepositoryLocal> { RoomRepoImpl(historyDao = get()) }
    }

    val retrofitModule = module {

        single<String>(named(BASE_URL_QUALIFIER)) { SKYENG_API_BASE_URL }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(get<String>(named(BASE_URL_QUALIFIER)))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<SkyengApi> { get<Retrofit>().create(SkyengApi::class.java) }
    }

    val databaseModule = module {

        single<HistoryDatabase> {
            Room.databaseBuilder(
                androidApplication(),
                HistoryDatabase::class.java,
                "history_database"
            ).build()
        }

        single<HistoryDao> { get<HistoryDatabase>().historyDao() }
    }

    val viewModelsModule = module {

        viewModel { MainActivityViewModel(repo = get(), repoLocal = get()) }
        viewModel { HistoryActivityViewModel(repoLocal = get()) }
    }
}