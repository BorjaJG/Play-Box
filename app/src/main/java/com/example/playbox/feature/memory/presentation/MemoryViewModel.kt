package com.example.playbox.feature.memory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playbox.feature.memory.domain.model.MemoryGame
import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository
import com.example.playbox.feature.memory.domain.usecase.SaveMemoryRecord
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MemoryUiState(
    val game: MemoryGame = MemoryGame.newGame(),
    val bestAttempts: Int? = null,
    val error: String? = null,
    val saving: Boolean = false,
)

class MemoryViewModel(repository: MemoryRecordRepository) : ViewModel() {
    private val saveRecord = SaveMemoryRecord(repository)
    private val _state = MutableStateFlow(MemoryUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.observeBestAttempts().collect { best ->
                    _state.update { it.copy(bestAttempts = best) }
                }
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                _state.update { it.copy(error = "No se ha podido leer tu récord.") }
            }
        }
    }

    fun reveal(index: Int) {
        val previous = _state.value.game
        val next = previous.reveal(index)
        _state.update { it.copy(game = next) }
        if (next.isFinished && !previous.isFinished) persistResult()
    }

    fun conceal() { _state.update { it.copy(game = it.game.conceal()) } }
    fun restart() { if (!_state.value.saving) _state.update { it.copy(game = MemoryGame.newGame(), error = null) } }

    fun persistResult() {
        val state = _state.value
        if (!state.game.isFinished || state.saving) return
        _state.update { it.copy(saving = true, error = null) }
        viewModelScope.launch {
            try {
                saveRecord(state.game.attempts)
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                _state.update { it.copy(error = "No se ha podido guardar tu récord. Puedes reintentarlo.") }
            } finally {
                _state.update { it.copy(saving = false) }
            }
        }
    }
}
