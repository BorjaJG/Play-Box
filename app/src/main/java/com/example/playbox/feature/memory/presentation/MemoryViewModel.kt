package com.example.playbox.feature.memory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playbox.feature.memory.domain.model.MemoryGame
import com.example.playbox.feature.memory.domain.usecase.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MemoryUiState(
    val game: MemoryGame,
    val bestAttempts: Int? = null,
    val error: String? = null,
    val saving: Boolean = false,
)

class MemoryViewModel(
    private val observeRecord: ObserveMemoryRecord,
    private val saveRecord: SaveMemoryRecord,
    private val startGame: StartMemoryGame,
    private val revealCard: RevealMemoryCard,
    private val concealCards: ConcealMemoryCards,
) : ViewModel() {
    private val _state = MutableStateFlow(MemoryUiState(game = startGame()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                observeRecord().collect { best ->
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
        val next = revealCard(previous, index)
        _state.update { it.copy(game = next) }
        if (next.isFinished && !previous.isFinished) persistResult()
    }

    fun conceal() { _state.update { it.copy(game = concealCards(it.game)) } }
    fun restart() { if (!_state.value.saving) _state.update { it.copy(game = startGame(), error = null) } }

    fun persistResult() {
        val state = _state.value
        if (!state.game.isFinished || state.saving) return
        _state.update { it.copy(saving = true, error = null) }
        viewModelScope.launch {
            try {
                saveRecord(state.game)
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                _state.update { it.copy(error = "No se ha podido guardar tu récord. Puedes reintentarlo.") }
            } finally {
                _state.update { it.copy(saving = false) }
            }
        }
    }
}
