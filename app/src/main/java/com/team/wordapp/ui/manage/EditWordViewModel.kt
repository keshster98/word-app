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
        word?.let {
            repo.updateWord(it.id!!, it.copy(
                title = title.value,
                meaning = meaning.value,
                synonyms = synonyms.value,
                details = details.value
            ))
        }
        viewModelScope.launch {
            _finish.emit(Unit)
        }
    }
}