package com.example.mydictionary.ui.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.data.MockRepo
import com.example.mydictionary.data.retrofit.ApiService
import com.example.mydictionary.data.retrofit.RepoImpl
import com.example.mydictionary.databinding.ActivityMainBinding
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.ui.base.BaseActivity
import com.example.mydictionary.ui.base.BasePresenter
import com.example.mydictionary.ui.base.BaseView
import com.example.mydictionary.ui.screens.main.recycler.OnWordClickListener
import com.example.mydictionary.ui.screens.main.recycler.WordsAdapter
import com.example.mydictionary.ui.screens.main.recycler.WordsDiffCallback

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val adapter by lazy { WordsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            val onSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord)
                }
            }
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun createPresenter(): BasePresenter<BaseView> {
        return MainActivityPresenter(RepoImpl())
    }

    override fun renderData(words: List<WordEntity>) {
        val wordsDiffCallback = WordsDiffCallback(adapter.data, words)
        val result = DiffUtil.calculateDiff(wordsDiffCallback, true)
        adapter.data = words
        result.dispatchUpdatesTo(adapter)
    }

    private fun initRecyclerView() {
        val onItemClickListener = object : OnWordClickListener {
            override fun onWordClick(item: WordEntity, position: Int) {
                Toast.makeText(this@MainActivity, "${item.text} is clicked", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onWordLongClick(item: WordEntity, itemView: View, position: Int) {
                Toast.makeText(this@MainActivity, "${item.text} long clicked", Toast.LENGTH_LONG)
                    .show()
            }
        }

        adapter.setOnItemClickListener(onItemClickListener)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}