package com.example.mydictionary

import com.example.model.entities.Meanings
import com.example.model.entities.WordEntity
import com.example.mydictionary.data.db.entities.HistoryEntity

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