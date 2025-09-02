package com.team.wordapp.ui.manage

import androidx.lifecycle.viewModelScope
import com.team.wordapp.data.model.Word
import kotlinx.coroutines.launch

class AddWordViewModel: BaseManageViewModel() {
    override fun submit() {
        val word = Word(
            title = title.value,
            meaning = meaning.value,
            synonyms = synonyms.value,
            details = details.value,
        )

        repo.addWord(word)

        viewModelScope.launch {
            _finish.emit(Unit)
        }
    }
}