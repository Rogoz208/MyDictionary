package com.example.mydictionary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydictionary.data.db.dao.WordsDao
import com.example.mydictionary.data.db.entities.WordRoomEntity

@Database(entities = [WordRoomEntity::class], version = 1, exportSchema = true)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun wordsDao(): WordsDao
}