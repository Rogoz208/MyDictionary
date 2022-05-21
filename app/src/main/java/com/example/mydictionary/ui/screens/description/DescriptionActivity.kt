package com.example.mydictionary.ui.screens.description

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.transition.Visibility
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.databinding.ActivityDescriptionBinding
import com.example.mydictionary.viewById
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class DescriptionActivity : AppCompatActivity(R.layout.activity_description) {

    private val binding by viewBinding(ActivityDescriptionBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)

        initActionBar()
        setupListeners()
        setData()
        testViewByIdDelegate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initActionBar() {
        supportActionBar?.apply {
            title = ""
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupListeners() {
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener {
            setData()
        }
    }

    private fun setData() {
        val bundle = intent.extras

        binding.descriptionHeaderTextView.text = bundle?.getString(WORD_EXTRA)
        binding.descriptionTextView.text = bundle?.getString(DESCRIPTION_EXTRA)

        val imageUrl = bundle?.getString(URL_EXTRA)
        if (imageUrl.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            loadImage(binding.descriptionImageView, imageUrl)
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        val callback = object : Callback {
            override fun onSuccess() {
                stopRefreshAnimationIfNeeded()
            }

            override fun onError(e: Exception?) {
                stopRefreshAnimationIfNeeded()
                imageView.setImageResource(R.drawable.ic_load_error)
            }

        }

        Picasso.get().load("https:$imageUrl")
            .placeholder(R.drawable.ic_no_photo).fit().centerCrop()
            .into(imageView, callback)
    }

    private fun testViewByIdDelegate() {
        val testTextView: TextView by viewById(R.id.test_viewbyid_text_view)
        testTextView.text = "IT WORK!!!"
    }

    companion object {

        private const val WORD_EXTRA = "WORD_EXTRA"
        private const val DESCRIPTION_EXTRA = "DESCRIPTION_EXTRA"
        private const val URL_EXTRA = "URL_EXTRA"

        fun getIntent(context: Context, word: String, description: String, url: String?): Intent =
            Intent(context, DescriptionActivity::class.java).apply {
                putExtra(WORD_EXTRA, word)
                putExtra(DESCRIPTION_EXTRA, description)
                putExtra(URL_EXTRA, url)
            }
    }
}