package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.model.MemoryGame

class RevealMemoryCard {
    operator fun invoke(game: MemoryGame, index: Int) = game.reveal(index)
}
