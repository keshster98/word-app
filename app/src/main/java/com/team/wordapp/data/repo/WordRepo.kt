package com.team.wordapp.data.repo

import com.team.wordapp.data.db.WordDao
import com.team.wordapp.data.model.Word
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class WordRepo(
    private val dao: WordDao
){
    fun getAllWords(): Flow<List<Word>> {
        return dao.getAllWords()
    }
    fun getWordById(id: Int) : Word? {
        return dao.getWordById(id)
    }
    fun addWord(word: Word) {
        dao.addWord(word)
    }
    fun updateWord(word: Word) {
        val updatedWord = word.copy(updatedAt = ZonedDateTime.now())
        dao.update(updatedWord)
    }
    fun deleteWord(id: Int) {
        dao.delete(id)
    }
    fun completed(word: Word) {
        val updatedWord = word.copy(isCompleted = true, updatedAt = ZonedDateTime.now())
        dao.update(updatedWord)
    }
    fun uncompleted(word: Word) {
        val updatedWord = word.copy(isCompleted = false, updatedAt = ZonedDateTime.now())
        dao.update(updatedWord)
    }
}