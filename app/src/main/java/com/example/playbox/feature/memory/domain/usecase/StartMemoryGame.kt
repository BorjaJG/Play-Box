package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.model.MemoryGame

class StartMemoryGame {
    operator fun invoke() = MemoryGame.newGame()
}
