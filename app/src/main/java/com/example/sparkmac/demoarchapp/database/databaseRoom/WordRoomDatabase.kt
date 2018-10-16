package com.example.sparkmac.demoarchapp.database.databaseRoom

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.example.sparkmac.demoarchapp.database.database.databaseDAO.WordDao
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word

@Database(entities = arrayOf(Word::class), version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private class PopulateDbAsync internal constructor(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private val mDao: WordDao

        init {
            mDao = db.wordDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.


            mDao.deleteAll()

            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            return null
        }
    }

    companion object {

        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        internal fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                WordRoomDatabase::class.java, "word_database")
                                // Wipes and rebuilds instead of migrating if no Migration object.
                                // Migration is not part of this codelab.
                                .fallbackToDestructiveMigration()
                                .addCallback(sRoomDatabaseCallback)
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         *
         * If you want to populate the database only when the database is created for the 1st time,
         * override RoomDatabase.Callback()#onCreate
         */
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }

}
