package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.model.MemoryGame
import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository

class SaveMemoryRecord(private val repository: MemoryRecordRepository) {
    suspend operator fun invoke(game: MemoryGame) {
        require(game.isFinished) { "Only completed games can save a record" }
        require(game.attempts >= 4) { "A completed four-pair game needs at least four attempts" }
        repository.saveIfBetter(game.attempts)
    }
}
