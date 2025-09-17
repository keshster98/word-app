package com.team.wordapp.ui.manage

import androidx.lifecycle.ViewModel
import com.team.wordapp.data.repo.WordRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseManageViewModel(
    val repo: WordRepo
) : ViewModel() {
    // Catch error message for the snack bar on the fragment
    val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    // Default state for the input fields
    val title = MutableStateFlow("")
    val meaning = MutableStateFlow("")
    val synonyms = MutableStateFlow<String?>(null)
    val details = MutableStateFlow<String?>(null)

    protected val _finish = MutableSharedFlow<Unit>()
    val finish = _finish.asSharedFlow()
    abstract fun submit()

    // Could not find a way to use getString and R for the error strings
    protected suspend fun validateInputs(): Boolean {
        val titleText = title.value.trim()
        val meaningText = meaning.value.trim()

        return when {
            titleText.isBlank() && meaningText.isBlank() -> {
                _error.emit("Title and Meaning cannot be empty!")
                false
            }
            titleText.isBlank() -> {
                _error.emit("Title cannot be empty!")
                false
            }
            meaningText.isBlank() -> {
                _error.emit("Meaning cannot be empty!")
                false
            }
            else -> true
        }
    }
}