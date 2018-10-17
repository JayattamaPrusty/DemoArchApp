package com.example.sparkmac.demoarchapp.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.sparkmac.demoarchapp.database.databaseDAO.WordDao
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import com.example.sparkmac.demoarchapp.database.databaseRoom.WordRoomDatabase
import android.os.AsyncTask


class WordRepository(private val application: Application) {


    private val db: WordRoomDatabase
    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>
    //private val mContext: Context

    init {

        db = WordRoomDatabase.getDatabase(application)!!
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
        //mContext = application.applicationContext
    }


    fun getAllWords() = mAllWords

    fun insert(word: Word) {

        insertAsyncTask(mWordDao).execute(word)

    }

    fun deleteAll(){

        insertAsyncTask1(mWordDao).execute()
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {

            mAsyncTaskDao.insert(params[0])

            return null
        }
    }

    private class insertAsyncTask1 internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {

            mAsyncTaskDao.deleteAll()

            return null
        }
    }


}