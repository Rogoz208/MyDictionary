package com.example.mydictionary.ui.screens.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.model.entities.WordEntity
import com.example.mydictionary.R
import com.example.mydictionary.convertMeaningsToString
import com.example.mydictionary.databinding.ActivityMainBinding
import com.example.mydictionary.ui.screens.description.DescriptionActivity
import com.example.mydictionary.ui.screens.history.HistoryActivity
import com.example.mydictionary.ui.screens.main.recycler.OnWordClickListener
import com.example.mydictionary.ui.screens.main.recycler.WordsAdapter
import com.example.mydictionary.ui.screens.main.recycler.WordsDiffCallback
import com.example.mydictionary.ui.screens.main.viewmodel.MainActivityViewModel
import com.example.mydictionary.ui.screens.main.viewmodel.MainActivityViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope
import java.util.concurrent.Executors

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

class MainActivity : AppCompatActivity(R.layout.activity_main), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val adapter by lazy { WordsAdapter() }
    private val viewModel: MainActivityViewModelContract.ViewModel by viewModel<MainActivityViewModel>()
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen = installSplashScreen()
            setupSplashScreen()
        }
        super.onCreate(savedInstanceState)

        initRecyclerView()
        initSearchFab()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_menu_item -> startHistoryActivity()
            R.id.search_history_menu_item -> searchInHistory()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupSplashScreen() {
        showSplashScreen(true)
        setupSlideLeftExitAnimation()
    }

    private fun showSplashScreen(shouldShow: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setKeepOnScreenCondition { shouldShow }
        }
    }

    private fun setupSlideLeftExitAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            ObjectAnimator.ofFloat(
                splashScreenViewProvider.view,
                View.TRANSLATION_X,
                0f,
                -splashScreenViewProvider.view.width.toFloat()
            ).apply {
                interpolator = AnticipateInterpolator()
                duration = 600
                doOnEnd { splashScreenViewProvider.remove() }
                start()
            }
        }
    }

    private fun initRecyclerView() {
        val onItemClickListener = object : OnWordClickListener {
            override fun onWordClick(item: WordEntity, position: Int) {
                openDescriptionActivity(item)
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
            val onSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord)
                }
            }

            showSearchDialogFragment(onSearchClickListener)
        }
    }

    private fun initViewModel() {
        viewModel.wordsLiveData.observe(this) { words: List<WordEntity> ->
            renderData(words)
        }

        viewModel.historyWordLiveData.observe(this) { word: WordEntity ->
            openDescriptionActivity(word)
        }

        viewModel.errorLiveData.observe(this) { errorMessage: String ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
        showSplashScreen(false)
    }

    private fun renderData(data: List<WordEntity>) {
        val wordsDiffCallback = WordsDiffCallback(adapter.data, data)
        val result = DiffUtil.calculateDiff(wordsDiffCallback, true)
        adapter.data = data
        result.dispatchUpdatesTo(adapter)
    }

    private fun startHistoryActivity() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun searchInHistory() {
        val onSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                viewModel.searchInHistory(searchWord)
            }
        }
        showSearchDialogFragment(onSearchClickListener)
    }

    private fun showSearchDialogFragment(onSearchClickListener: SearchDialogFragment.OnSearchClickListener) {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
        searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
    }

    private fun openDescriptionActivity(item: WordEntity) {
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                item.text!!,
                convertMeaningsToString(item.meanings!!),
                item.meanings!![0].imageUrl
            )
        )
    }
}