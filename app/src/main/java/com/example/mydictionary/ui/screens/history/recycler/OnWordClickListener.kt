package com.example.mydictionary.ui.screens.history.recycler

import android.view.View
import com.example.mydictionary.domain.entities.WordEntity

interface OnWordClickListener {

    fun onWordClick(item: WordEntity, position: Int)

    fun onWordLongClick(item: WordEntity, itemView: View, position: Int)
}