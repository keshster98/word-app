package com.team.wordapp.ui.manage

import androidx.lifecycle.viewModelScope
import com.team.wordapp.data.model.Word
import kotlinx.coroutines.launch

class EditWordViewModel: BaseManageViewModel() {
    private var word: Word? = null
    fun getWord(id: Int) {
        repo.getWordById(id)?.let {
            word = it
            title.value = it.title
            meaning.value = it.meaning
            synonyms.value = it.synonyms
            details.value = it.details
        }
    }
    override fun submit() {
        viewModelScope.launch {
            if (!validateInputs()) return@launch

            word?.let {
                repo.updateWord(
                    it.id!!,
                    it.copy(
                        title = title.value.trim(),
                        meaning = meaning.value.trim(),
                        synonyms = synonyms.value,
                        details = details.value
                    )
                )
                _finish.emit(Unit)
            }
        }
    }
}