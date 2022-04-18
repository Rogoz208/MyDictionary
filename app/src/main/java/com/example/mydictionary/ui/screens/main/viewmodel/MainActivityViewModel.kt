package com.example.mydictionary.ui.screens.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivityViewModel(private val repo: Repository) :
    MainActivityViewModelContract.ViewModel() {

    override val wordsLiveData: MutableLiveData<List<WordEntity>> = MutableLiveData<List<WordEntity>>()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    override fun getData(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val words = repo.getData(word)
                wordsLiveData.postValue(words)
            } catch (e: IOException) {
                errorLiveData.postValue(e.localizedMessage)
            }
        }
    }
}