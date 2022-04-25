package com.example.mydictionary.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "word_table", indices = [Index("text", unique = true)])
data class WordRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "translation")
    val translation: String
)
