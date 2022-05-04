package com.example.mydictionary.ui.screens.main.recycler

import android.view.View
import com.example.model.entities.WordEntity

interface OnWordClickListener {

    fun onWordClick(item: WordEntity, position: Int)

    fun onWordLongClick(item: WordEntity, itemView: View, position: Int)
}