package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.model.MemoryGame

class ConcealMemoryCards {
    operator fun invoke(game: MemoryGame) = game.conceal()
}
