package com.team.wordapp.ui.home

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    protected val repo: WordRepo = WordRepo.getInstance()
): ViewModel() {

    var searchState = ""
    var sortState1 = 0
    var sortState2 = 0
    val notCompletedWords = MutableStateFlow<List<Word>>(emptyList())
    val completedWords = MutableStateFlow<List<Word>>(emptyList())

    fun List<Word>.searchSort(search: String, sort1: Int, sort2: Int): List<Word> {
        var list = this
        if (search.isNotEmpty()) { list = list.filter { it.title.contains(search, ignoreCase = true) } }
        list = when (sort2) {
            0 -> {
                if (sort1 == 0) list.sortedBy { it.title }
                else list.sortedByDescending { it.title }
            }
            1 -> {
                if (sort1 == 0) list.sortedBy { it.createdAt }
                else list.sortedByDescending { it.createdAt }
            }
            else -> list
        }
        return list
    }

    init {
        refresh()
    }

    fun refresh() {
        getNotCompletedWords()
        getCompletedWords()
    }

    fun getNotCompletedWords() {
        notCompletedWords.value = repo.getAllWords().filter { !it.isCompleted }.searchSort(searchState, sortState1, sortState2)
    }

    fun getCompletedWords() {
        completedWords.value = repo.getAllWords().filter { it.isCompleted }.searchSort(searchState, sortState1, sortState2)
    }
}