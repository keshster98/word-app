package com.team.wordapp.ui.home

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val repo: WordRepo = WordRepo.getInstance()
): ViewModel() {

    var searchState = ""
    var sortState1 = 0
    var sortState2 = 0
    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words = _words.asStateFlow()

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
        getWords(searchState, sortState1, sortState2)
    }

    fun getWords(search: String = searchState, sort1: Int = sortState1, sort2: Int = sortState2) {
        _words.value = repo.getAllWords().searchSort(search, sort1, sort2)
    }
}