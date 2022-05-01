package com.example.mydictionary.ui.screens.main.viewmodel

import androidx.lifecycle.LiveData
import com.example.mydictionary.domain.entities.WordEntity

interface MainActivityViewModelContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val wordsLiveData: LiveData<List<WordEntity>>
        abstract val historyWordLiveData: LiveData<WordEntity>
        abstract val errorLiveData: LiveData<String>

        abstract fun getData(word: String)
        abstract fun searchInHistory(word: String)
    }
}