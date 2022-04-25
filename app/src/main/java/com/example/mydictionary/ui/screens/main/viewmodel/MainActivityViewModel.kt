package com.example.mydictionary.ui.screens.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repo: Repository) :
    MainActivityViewModelContract.ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    override val wordsLiveData: MutableLiveData<List<WordEntity>> =
        MutableLiveData<List<WordEntity>>()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    override fun getData(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val words = repo.getData(word)
            wordsLiveData.postValue(words)
        }
    }
}