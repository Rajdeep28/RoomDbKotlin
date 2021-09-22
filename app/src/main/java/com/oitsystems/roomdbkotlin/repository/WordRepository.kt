package com.oitsystems.roomdbkotlin.repository

import androidx.annotation.WorkerThread
import com.oitsystems.roomdbkotlin.dao.WordDao
import com.oitsystems.roomdbkotlin.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(var wordDao: WordDao) {

    var allWords: Flow<List<Word>> = wordDao.getAlphabetizedWord()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}