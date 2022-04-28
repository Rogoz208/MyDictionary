package com.example.mydictionary.ui.screens.history.viewmodel

import androidx.lifecycle.LiveData
import com.example.mydictionary.data.db.entities.HistoryEntity
import com.example.mydictionary.domain.entities.WordEntity

interface HistoryActivityViewModelContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val wordsLiveData: LiveData<List<WordEntity>>
        abstract val errorLiveData: LiveData<String>

        abstract fun getData(word: String)
    }
}