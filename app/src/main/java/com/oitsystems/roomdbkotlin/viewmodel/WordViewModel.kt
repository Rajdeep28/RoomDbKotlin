package com.oitsystems.roomdbkotlin.viewmodel

import androidx.lifecycle.*
import com.oitsystems.roomdbkotlin.entity.Word
import com.oitsystems.roomdbkotlin.repository.WordRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(var repository: WordRepository): ViewModel() {

    var allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /* launching a new coroutine to insert the data in a non-blocking way*/

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(var repository: WordRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}