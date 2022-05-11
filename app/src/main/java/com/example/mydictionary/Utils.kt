package com.example.mydictionary

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.model.entities.Meanings
import com.example.model.entities.WordEntity
import com.example.mydictionary.data.db.entities.HistoryEntity
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComa = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComa += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComa
}

fun mapHistoryEntityToWordEntityList(list: List<HistoryEntity>): List<WordEntity> {
    val wordsList = ArrayList<WordEntity>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            val meanings = listOf(
                Meanings(
                    com.example.model.entities.Translation(
                        entity.translation
                    ), null
                )
            )
            val wordEntity =
                WordEntity(entity.hashCode(), entity.word, meanings)
            wordsList.add(wordEntity)
        }
    }
    return wordsList
}

fun mapHistoryEntityToWordEntity(entity: HistoryEntity?): WordEntity? = if (entity != null) {
    val meanings = listOf(
        Meanings(
            com.example.model.entities.Translation(
                entity.translation
            ), null
        )
    )
    WordEntity(entity.hashCode(), entity.word, meanings)
} else {
    null
}

fun mapWordEntityToHistoryEntity(word: WordEntity): HistoryEntity =
    HistoryEntity(word.text.toString(), word.meanings?.get(0)?.translation?.translation)

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {

    private var rootRef: WeakReference<View>? = null
    private var viewRef: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        val currentRoot = rootGetter()

        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    return view
                }
                throw IllegalStateException("Cannot get View, there is no root yet")
            }

            view = currentRoot.findViewById(viewId)
            viewRef = view
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) { "View with id \"$viewId\" not found in root" }
        return view
    }
}

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}