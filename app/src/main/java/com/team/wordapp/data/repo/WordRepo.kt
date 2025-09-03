package com.team.wordapp.data.repo

import com.team.wordapp.data.model.Word

class WordRepo private constructor() {
    // To store all the words
    private val map = mutableMapOf<Int, Word>()
    // To generate product IDs
    private var counter = 0

    // Creates 10 random word cards
//    init {
//        generateRandomWords(10)
//    }

    // Adds a word to the repo
    fun addWord(word: Word) {
        val id = ++counter
        map[id] = word.copy(id = id)
    }

    // Retrieves all words
    fun getAllWords(): List<Word> = map.values.toList()

    // Retrieves a specific word from the repo
    fun getWordById(id: Int): Word? {
        return map[id]
    }

    // Updates a specific word from the repo
    fun updateWord(id: Int, word: Word) {
        if (map.containsKey(id)) {
            map[id] = word.copy(id = id)
        }
    }

    // Deletes a specific word from the repo
    fun deleteWord(id: Int) {
        map.remove(id)
    }

    // Random word card generator
    fun generateRandomWords(n: Int) {
        repeat(n) {
            val id = ++counter
            map[id] = Word(
                title = "Title: $it",
                meaning = "Definition: $it",
            )
        }
    }

    // Toggle isCompleted Boolean
    fun completed(word: Word) {
        if (map.containsKey(word.id)) {
            map.replace(word.id!!, word.copy(isCompleted = true))
        }
    }

    // Word repo instance creator
    companion object {
        private var instance: WordRepo? = null
        fun getInstance(): WordRepo {
            if (instance == null) {
                instance = WordRepo()
            }
            return instance!!
        }
    }
}

