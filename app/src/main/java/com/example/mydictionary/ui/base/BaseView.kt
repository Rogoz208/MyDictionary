package com.example.mydictionary.ui.base

import com.example.mydictionary.domain.entities.WordEntity

interface BaseView {
    fun renderData(words: List<WordEntity>)
}