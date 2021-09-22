package com.oitsystems.roomdbkotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oitsystems.roomdbkotlin.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("Select * from word_table ORDER BY word ASC")
    fun getAlphabetizedWord(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("Delete from word_table")
    suspend fun deleteAll()
}