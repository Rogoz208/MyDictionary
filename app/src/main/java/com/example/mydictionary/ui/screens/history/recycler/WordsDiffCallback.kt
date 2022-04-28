package com.example.mydictionary.ui.screens.history.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.mydictionary.domain.entities.WordEntity

class WordsDiffCallback(
    private val oldList: List<WordEntity>,
    private val newList: List<WordEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text &&
                oldList[oldItemPosition].meanings?.get(0) == newList[newItemPosition].meanings?.get(
            0
        )
    }
}