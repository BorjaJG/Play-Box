package com.example.playbox.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.example.playbox.core.domain.model.GameDefinition
import com.example.playbox.feature.home.domain.usecase.GetGames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(val games: List<GameDefinition> = emptyList())

class HomeViewModel(getGames: GetGames) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState(getGames()))
    val state = _state.asStateFlow()
}
