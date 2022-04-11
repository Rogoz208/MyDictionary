package com.example.mydictionary

import android.app.Application
import android.content.Context
import com.example.mydictionary.data.retrofit.RepoImpl
import com.example.mydictionary.ui.base.BasePresenter
import com.example.mydictionary.ui.base.BaseView
import com.example.mydictionary.ui.screens.main.MainActivityPresenter

class App : Application() {

    val mainActivityPresenter: BasePresenter<BaseView> by lazy { MainActivityPresenter(RepoImpl()) }
}

val Context.app: App
    get() = applicationContext as App