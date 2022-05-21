package com.example.mydictionary.ui.screens.history

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.convertMeaningsToString
import com.example.mydictionary.databinding.ActivityHistoryBinding
import com.example.model.entities.WordEntity
import com.example.mydictionary.ui.screens.description.DescriptionActivity
import com.example.mydictionary.ui.screens.history.recycler.WordsDiffCallback
import com.example.mydictionary.ui.screens.history.recycler.OnWordClickListener
import com.example.mydictionary.ui.screens.history.recycler.WordsAdapter
import com.example.mydictionary.ui.screens.history.viewmodel.HistoryActivityViewModel
import com.example.mydictionary.ui.screens.history.viewmodel.HistoryActivityViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

class HistoryActivity : AppCompatActivity(R.layout.activity_history), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val binding by viewBinding(ActivityHistoryBinding::bind)
    private val adapter by lazy { WordsAdapter() }
    private val viewModel: HistoryActivityViewModelContract.ViewModel by viewModel<HistoryActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)

        initActionBar()
        initRecyclerView()
        initViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initActionBar() {
        supportActionBar?.apply {
            title = getString(R.string.history_activity)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initRecyclerView() {
        val onItemClickListener = object : OnWordClickListener {
            override fun onWordClick(item: WordEntity, position: Int) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@HistoryActivity,
                        item.text!!,
                        convertMeaningsToString(item.meanings!!),
                        item.meanings!![0].imageUrl
                    )
                )
            }

            override fun onWordLongClick(item: WordEntity, itemView: View, position: Int) {
                Toast.makeText(
                    this@HistoryActivity,
                    "${item.text} is long clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        adapter.setOnItemClickListener(onItemClickListener)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.wordsLiveData.observe(this) { words: List<WordEntity> ->
            renderData(words)
        }
        viewModel.getData("")
    }

    private fun renderData(data: List<WordEntity>) {
        val historyEntitiesDiffCallback = WordsDiffCallback(adapter.data, data)
        val result = DiffUtil.calculateDiff(historyEntitiesDiffCallback, true)
        adapter.data = data
        result.dispatchUpdatesTo(adapter)
    }
}