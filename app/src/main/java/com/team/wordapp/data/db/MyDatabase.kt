package com.team.wordapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team.wordapp.data.model.Word

@Database(
    entities = [Word::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao
    companion object {
        const val NAME = "my_database"
    }
}