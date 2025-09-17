package com.team.wordapp.ui.confirmationDelete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.team.wordapp.MyApp
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfirmationViewModel(
    private val repo: WordRepo
): ViewModel() {
    fun delete(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteWord(wordId)
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApp).repo
                ConfirmationViewModel(repo = myRepository)
            }
        }
    }
}