package com.example.sparkmac.demoarchapp.database.databaseEntity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")

data class Word(@ColumnInfo(name = "word") val data_word: String) {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    fun getWord()=data_word
}