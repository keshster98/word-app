package com.team.wordapp.ui.details

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo

class WordDetailsViewModel: ViewModel() {
    private val repo = WordRepo.getInstance()

    fun getWord(id: Int): Word {
        return repo.getWordById(id)!!
    }
}