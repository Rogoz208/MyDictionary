package com.example.mydictionary.ui.base

interface BasePresenter<V : BaseView> {

    fun attachView(view: V)
    fun detachView(view: V)

    fun getData(word: String)
}