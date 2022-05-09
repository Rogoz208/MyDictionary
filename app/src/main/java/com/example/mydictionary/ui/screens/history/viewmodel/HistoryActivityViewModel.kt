package com.example.mydictionary.ui.screens.history.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.model.entities.WordEntity
import com.example.model.repos.RepositoryLocal
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryActivityViewModel(private val repoLocal: RepositoryLocal) :
    HistoryActivityViewModelContract.ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    override val wordsLiveData: MutableLiveData<List<WordEntity>> =
        MutableLiveData<List<WordEntity>>()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    override fun getData(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val words = repoLocal.getData(word)
            wordsLiveData.postValue(words)
        }
    }
}