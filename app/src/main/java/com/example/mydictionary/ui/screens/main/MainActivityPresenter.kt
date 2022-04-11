package com.example.mydictionary.ui.screens.main

import com.example.mydictionary.domain.repos.Repository
import com.example.mydictionary.ui.base.BasePresenter
import com.example.mydictionary.ui.base.BaseView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityPresenter<V : BaseView>(private val repo: Repository) : BasePresenter<V> {

    private var currentView: V? = null

    private var wordsDisposable: Disposable? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {

        wordsDisposable = repo.getData(word)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    currentView?.renderData(it)
                },
                onError = {

                }
            )
    }
}