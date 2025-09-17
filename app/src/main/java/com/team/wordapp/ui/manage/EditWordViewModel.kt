package com.team.wordapp.ui.manage

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.team.wordapp.data.model.Word
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.team.wordapp.MyApp
import java.time.ZonedDateTime

class EditWordViewModel(
    repo: WordRepo
): BaseManageViewModel(repo) {
    private var word: Word? = null

    fun getWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getWordById(id)?.let {
                word = it
                title.value = it.title
                meaning.value = it.meaning
                synonyms.value = it.synonyms
                details.value = it.details
            }
        }
    }

    override fun submit() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!validateInputs()) return@launch

            val updated = word?.copy(
                title = title.value.trim(),
                meaning = meaning.value.trim(),
                synonyms = synonyms.value,
                details = details.value,
                updatedAt = ZonedDateTime.now()
            ) ?: return@launch

            repo.updateWord(updated)
            _finish.emit(Unit)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApp).repo
                EditWordViewModel(repo = myRepository)
            }
        }
    }
}