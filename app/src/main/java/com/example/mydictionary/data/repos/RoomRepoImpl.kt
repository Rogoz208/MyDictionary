package com.example.mydictionary.data.repos

import com.example.mydictionary.data.db.dao.HistoryDao
import com.example.mydictionary.data.db.entities.HistoryEntity
import com.example.mydictionary.domain.entities.Meanings
import com.example.mydictionary.domain.entities.Translation
import com.example.mydictionary.domain.entities.WordEntity
import com.example.mydictionary.domain.repos.RepositoryLocal

class RoomRepoImpl(private val historyDao: HistoryDao) : RepositoryLocal {

    override suspend fun saveToDb(word: WordEntity) {
        historyDao.insertWord(mapWordEntityToHistoryEntity(word))
    }

    override suspend fun getData(word: String): List<WordEntity> {
        return mapHistoryEntityToWordEntity(historyDao.getAll())
    }

    private fun mapHistoryEntityToWordEntity(list: List<HistoryEntity>): List<WordEntity> {
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

    private fun mapWordEntityToHistoryEntity(word: WordEntity): HistoryEntity =
        HistoryEntity(word.text.toString(), word.meanings?.get(0)?.translation?.translation)
}