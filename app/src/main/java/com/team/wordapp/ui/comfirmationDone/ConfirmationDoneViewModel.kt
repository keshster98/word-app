package com.team.wordapp.ui.comfirmationDone

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo

class ConfirmationDoneViewModel : ViewModel() {
    private val repo = WordRepo.getInstance()

    fun done(word: Word) {
        repo.completed(word)
    }
}