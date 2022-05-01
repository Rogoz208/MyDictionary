package com.example.mydictionary

import com.example.mydictionary.data.db.entities.HistoryEntity
import com.example.mydictionary.domain.entities.Meanings
import com.example.mydictionary.domain.entities.Translation
import com.example.mydictionary.domain.entities.WordEntity

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

fun mapHistoryEntityToWordEntity(list: List<HistoryEntity>): List<WordEntity> {
    val wordsList = ArrayList<WordEntity>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            val meanings = listOf(Meanings(Translation(entity.translation), null))
            val wordEntity = WordEntity(entity.hashCode(), entity.word, meanings)
            wordsList.add(wordEntity)
        }
    }
    return wordsList
}

fun mapWordEntityToHistoryEntity(word: WordEntity): HistoryEntity =
    HistoryEntity(word.text.toString(), word.meanings?.get(0)?.translation?.translation)