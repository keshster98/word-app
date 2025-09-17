package com.team.wordapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.team.wordapp.MyApp
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    val repo: WordRepo
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
                if (sort1 == 0) list.sortedBy { it.updatedAt }
                else list.sortedByDescending { it.updatedAt }
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

    fun getCompletedWords() {
        viewModelScope.launch {
            repo.getAllWords().collect { words ->
                completedWords.value = words
                    .filter { it.isCompleted }
                    .searchSort(searchState, sortState1, sortState2)
            }
        }
    }

    fun getNotCompletedWords() {
        viewModelScope.launch {
            repo.getAllWords().collect { words ->
                notCompletedWords.value = words
                    .filter { !it.isCompleted }
                    .searchSort(searchState, sortState1, sortState2)
            }
        }
    }

    fun completedSize(): Boolean {
        if(completedWords.value.isEmpty()) return true
        else return false
    }

    fun notCompletedSize(): Boolean {
        if(notCompletedWords.value.isEmpty()) return true
        else return false
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApp).repo
                HomeViewModel(repo = myRepository)
            }
        }
    }
}