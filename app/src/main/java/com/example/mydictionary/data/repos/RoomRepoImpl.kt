package com.example.mydictionary.data.repos

import com.example.mydictionary.data.db.dao.HistoryDao
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.RepositoryLocal
import com.example.mydictionary.mapHistoryEntityToWordEntity
import com.example.mydictionary.mapWordEntityToHistoryEntity

class RoomRepoImpl(private val historyDao: HistoryDao) : RepositoryLocal {

    override suspend fun saveToDb(word: WordEntity) {
        historyDao.insertWord(mapWordEntityToHistoryEntity(word))
    }

    override suspend fun getData(word: String): List<WordEntity> {
        return mapHistoryEntityToWordEntity(historyDao.getAll())
    }
}