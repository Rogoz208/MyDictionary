package com.example.mydictionary.ui.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.databinding.ActivityMainRecyclerviewItemBinding
import com.example.model.entities.WordEntity

class WordViewHolder(parent: ViewGroup, private val clickListener: OnWordClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_main_recyclerview_item, parent, false)
    ) {

    private val binding by viewBinding(ActivityMainRecyclerviewItemBinding::bind)

    fun bind(word: WordEntity) {
        binding.headerTextView.text = word.text
        binding.descriptionTextView.text = word.meanings?.get(0)?.translation?.translation

        itemView.setOnClickListener {
            clickListener.onWordClick(word, this.layoutPosition)
        }
        itemView.setOnLongClickListener {
            clickListener.onWordLongClick(word, itemView, this.layoutPosition)
            true
        }
    }

}