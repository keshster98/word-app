package com.team.wordapp.ui.manage

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.team.wordapp.MyApp
import kotlinx.coroutines.Dispatchers

class AddWordViewModel(
    repo: WordRepo
): BaseManageViewModel(repo) {
    override fun submit() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!validateInputs()) return@launch

            val word = Word(
                title = title.value.trim(),
                meaning = meaning.value.trim(),
                synonyms = synonyms.value,
                details = details.value,
            )

            repo.addWord(word)
            _finish.emit(Unit)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApp).repo
                AddWordViewModel(repo = myRepository)
            }
        }
    }
}