package com.example.mydictionary.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydictionary.data.db.entities.WordRoomEntity

@Dao
interface WordsDao {

    @Query("SELECT * FROM word_table")
    fun getAllWords(): List<WordRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordRoomEntity)
}