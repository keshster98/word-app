package com.team.wordapp.ui.home

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val repo: WordRepo = WordRepo.getInstance()
): ViewModel() {

    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words = _words.asStateFlow()

    init {
        getWords()
    }

    fun getWords() {
        _words.value = repo.getAllWords()
    }

}