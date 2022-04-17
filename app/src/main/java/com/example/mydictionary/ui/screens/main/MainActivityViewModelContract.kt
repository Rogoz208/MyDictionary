package com.example.mydictionary.ui.screens.main

import androidx.lifecycle.LiveData
import com.example.mydictionary.domain.entities.WordEntity

interface MainActivityViewModelContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val wordsLiveData: LiveData<List<WordEntity>>

        abstract fun getData(word: String)
    }
}