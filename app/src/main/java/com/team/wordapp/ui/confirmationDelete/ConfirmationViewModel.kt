package com.team.wordapp.ui.confirmationDelete

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.repo.WordRepo

class ConfirmationViewModel: ViewModel() {
    private val repo = WordRepo.getInstance()

    fun delete(wordId: Int) {
        repo.deleteWord(wordId)
    }
}