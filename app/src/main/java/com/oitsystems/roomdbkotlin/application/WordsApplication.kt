package com.oitsystems.roomdbkotlin.application

import android.app.Application
import com.oitsystems.roomdbkotlin.database.WordRoomDatabase
import com.oitsystems.roomdbkotlin.repository.WordRepository

class WordsApplication: Application() {
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}