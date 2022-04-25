package com.example.mydictionary.data.db.utils

import com.example.mydictionary.data.db.entities.WordRoomEntity
import com.example.mydictionary.domain.entities.WordEntity

object WordsRoomEntityConverter {
    fun convertToRoomEntity(word: WordEntity): WordRoomEntity =
        WordRoomEntity(
            text = word.text.toString(),
            translation = word.meanings?.get(0)?.translation?.translation.toString()
        )
}