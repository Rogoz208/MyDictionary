package com.example.mydictionary.ui.screens.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import com.example.mydictionary.domain.repos.RepositoryLocal
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repo: Repository, private val repoLocal: RepositoryLocal) :
    MainActivityViewModelContract.ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    override val wordsLiveData: MutableLiveData<List<WordEntity>> =
        MutableLiveData<List<WordEntity>>()
    override val historyWordLiveData: MutableLiveData<WordEntity> = MutableLiveData<WordEntity>()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    override fun getData(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val words = repo.getData(word)
            repoLocal.saveToDb(words[0])
            wordsLiveData.postValue(words)
        }
    }

    override fun searchInHistory(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val historyWord = repoLocal.searchByWord(word)
            if (historyWord != null) {
                historyWordLiveData.postValue(historyWord)
            } else {
                errorLiveData.postValue("Word is not found in history")
            }
        }
    }
}