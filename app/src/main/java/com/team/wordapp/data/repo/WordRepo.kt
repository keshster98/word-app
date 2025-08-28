package com.team.wordapp.data.repo

import com.team.wordapp.data.model.Word

class WordRepo private constructor() {
    // To store all the words
    val items: MutableMap<Int, Word> = mutableMapOf()
    // To generate product IDs
    var counter = 0

    // Creates 10 random word cards
    init {
        generateRandomWords(10)
    }

    // Adds a word to the repo
    fun addWord(word: Word) {
        // To ensure ID begins with 1
        counter++
        items[counter] = word.copy(id = counter)
    }

    // Retrieves all words from the repo
    fun getAllWords() = items.values.toList()

    // Retrieves a specific word from the repo
    fun getWord(id: Int) = items[id]

    // Updates a specific word from the repo
    fun updateWord(word: Word) {
        items[word.id!!] = word
    }

    // Deletes a specific word from the repo
    fun deleteWord(id: Int) {
        items.remove(id)
    }

    // Random word card generator
    fun generateRandomWords(n: Int) {
        repeat(n) {
            val word = Word(
                title = "Title: $it",
                meaning = "Definition: $it",
            )
            addWord(word)
        }
    }

    // Word repo instance creator
    companion object {
        private var instance: WordRepo? = null

        @Synchronized
        fun getInstance(): WordRepo {
            if (instance == null) {
                instance = WordRepo()
            }
            return instance!!
        }
    }
}