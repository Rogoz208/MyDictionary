package com.example.mydictionary.ui.screens.history.viewmodel

import androidx.lifecycle.LiveData
import com.example.model.entities.WordEntity

interface HistoryActivityViewModelContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val wordsLiveData: LiveData<List<WordEntity>>
        abstract val errorLiveData: LiveData<String>

        abstract fun getData(word: String)
    }
}