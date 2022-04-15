package com.example.mydictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydictionary.domain.entities.WordEntity

abstract class BaseActivity(layout: Int) : AppCompatActivity(layout), BaseView {

    protected lateinit var presenter: BasePresenter<BaseView>

    protected abstract fun createPresenter(): BasePresenter<BaseView>

    abstract override fun renderData(words: List<WordEntity>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}