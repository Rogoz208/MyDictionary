package com.example.mydictionary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydictionary.data.db.dao.HistoryDao
import com.example.mydictionary.data.db.entities.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = true)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}