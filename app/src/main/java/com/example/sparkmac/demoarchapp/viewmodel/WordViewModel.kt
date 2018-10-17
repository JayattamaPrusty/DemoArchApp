package com.example.sparkmac.demoarchapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import android.arch.lifecycle.LiveData
import com.example.sparkmac.demoarchapp.repository.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    internal val allWords: LiveData<List<Word>>

    init {
        mRepository = WordRepository(application)
        allWords = mRepository.getAllWords()
    }

     fun getAllWords(): LiveData<List<Word>> {
        return allWords
    }

    internal fun insert(word: Word) {

        mRepository.insert(word)
    }

    internal fun deleteAll() {

        mRepository.deleteAll()
    }
}