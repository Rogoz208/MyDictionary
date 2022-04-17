package com.example.mydictionary.ui.screens.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel(private val repo: Repository) :
    MainActivityViewModelContract.ViewModel() {

    private var wordsDisposable: Disposable? = null

    override val wordsLiveData: MutableLiveData<List<WordEntity>> =
        MutableLiveData<List<WordEntity>>()

    override fun getData(word: String) {
        wordsDisposable = repo.getData(word)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { data: List<WordEntity> ->
                    wordsLiveData.postValue(data)
                },
                onError = {

                }
            )
    }
}