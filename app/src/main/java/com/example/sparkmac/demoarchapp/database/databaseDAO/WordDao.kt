package com.example.sparkmac.demoarchapp.database.databaseDAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word

@Dao
interface WordDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>


    @Query(  "select count(*) from word_table WHERE word_table.word = :rowvalue")
    fun duplicateRows(rowvalue:String):Long
}