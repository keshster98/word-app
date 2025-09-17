package com.team.wordapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.team.wordapp.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>
    @Query("SELECT * FROM words where id = :id")
    fun getWordById(id: Int): Word?
    @Insert
    fun addWord(word: Word)
    @Update
    fun update(word: Word)
    @Query("DELETE FROM words WHERE id = :id")
    fun delete(id: Int)
}