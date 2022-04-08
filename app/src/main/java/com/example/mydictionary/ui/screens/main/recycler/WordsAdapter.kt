package com.example.mydictionary.ui.screens.main.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.domain.entities.WordEntity

class WordsAdapter : RecyclerView.Adapter<WordViewHolder>() {

    var data: List<WordEntity> = ArrayList()
        get() = ArrayList(field)

    private var clickListener: OnWordClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(parent, clickListener!!)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): WordEntity {
        return data[position]
    }

    fun setOnItemClickListener(listener: OnWordClickListener) {
        clickListener = listener
    }
}