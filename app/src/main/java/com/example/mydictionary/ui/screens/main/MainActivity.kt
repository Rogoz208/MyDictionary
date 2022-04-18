package com.example.mydictionary.ui.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.databinding.ActivityMainBinding
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.ui.screens.main.recycler.OnWordClickListener
import com.example.mydictionary.ui.screens.main.recycler.WordsAdapter
import com.example.mydictionary.ui.screens.main.recycler.WordsDiffCallback
import com.example.mydictionary.ui.screens.main.viewmodel.MainActivityViewModel
import com.example.mydictionary.ui.screens.main.viewmodel.MainActivityViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val adapter by lazy { WordsAdapter() }
    private val viewModel: MainActivityViewModelContract.ViewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        initSearchFab()
        initViewModel()
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

    private fun initSearchFab() {
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            val onSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord)
                }
            }
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    private fun initViewModel() {
        viewModel.wordsLiveData.observe(this) { words: List<WordEntity> ->
            renderData(words)
        }

        viewModel.errorLiveData.observe(this) { errorMessage: String ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderData(words: List<WordEntity>) {
        val wordsDiffCallback = WordsDiffCallback(adapter.data, words)
        val result = DiffUtil.calculateDiff(wordsDiffCallback, true)
        adapter.data = words
        result.dispatchUpdatesTo(adapter)
    }
}