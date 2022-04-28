package com.example.mydictionary.ui.screens.history.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.data.db.entities.HistoryEntity
import com.example.mydictionary.databinding.ActivityMainRecyclerviewItemBinding
import com.example.mydictionary.domain.entities.WordEntity

class WordViewHolder(parent: ViewGroup, private val clickListener: OnWordClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_main_recyclerview_item, parent, false)
    ) {

    private val binding by viewBinding(ActivityMainRecyclerviewItemBinding::bind)

    fun bind(entity: WordEntity) {
        binding.headerTextView.text = entity.text
        binding.descriptionTextView.text = entity.meanings?.get(0)?.translation?.translation

        itemView.setOnClickListener {
            clickListener.onWordClick(entity, this.layoutPosition)
        }
        itemView.setOnLongClickListener {
            clickListener.onWordLongClick(entity, itemView, this.layoutPosition)
            true
        }
    }

}