package com.example.mydictionary.data.db.dao

import androidx.room.*
import com.example.mydictionary.data.db.entities.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history_table")
    suspend fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_table WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): HistoryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryEntity>)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}