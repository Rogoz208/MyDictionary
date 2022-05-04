package com.example.mydictionary.data.repos

import com.example.model.entities.WordEntity
import com.example.model.repos.RepositoryLocal
import com.example.mydictionary.data.db.dao.HistoryDao
import com.example.mydictionary.mapHistoryEntityToWordEntity
import com.example.mydictionary.mapHistoryEntityToWordEntityList
import com.example.mydictionary.mapWordEntityToHistoryEntity

class RoomRepoImpl(private val historyDao: HistoryDao) : RepositoryLocal {

    override suspend fun saveToDb(word: WordEntity) {
        historyDao.insertWord(mapWordEntityToHistoryEntity(word))
    }

    override suspend fun searchByWord(word: String): WordEntity? {
        return mapHistoryEntityToWordEntity(historyDao.getDataByWord(word))
    }

    override suspend fun getData(word: String): List<WordEntity> {
        return mapHistoryEntityToWordEntityList(historyDao.getAll())
    }
}