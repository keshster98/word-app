package com.team.wordapp.ui.manage

import androidx.lifecycle.viewModelScope
import com.team.wordapp.data.model.Word
import kotlinx.coroutines.launch

class AddWordViewModel: BaseManageViewModel() {
    override fun submit() {
        viewModelScope.launch {
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
}