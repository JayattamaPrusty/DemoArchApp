package com.example.sparkmac.demoarchapp.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.sparkmac.demoarchapp.database.database.databaseDAO.WordDao
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import com.example.sparkmac.demoarchapp.database.databaseRoom.WordRoomDatabase
import android.os.AsyncTask



class WordRepository(private val application: Application) {


    private val db: WordRoomDatabase
    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>

    init {

        db= WordRoomDatabase.getDatabase(application)!!
        mWordDao= db.wordDao()
        mAllWords= mWordDao.getAllWords()
    }


    fun getAllWords() = mAllWords

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }


}