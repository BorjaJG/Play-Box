package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository

class SaveMemoryRecord(private val repository: MemoryRecordRepository) {
    suspend operator fun invoke(attempts: Int) {
        require(attempts >= 4) { "A completed four-pair game needs at least four attempts" }
        repository.saveIfBetter(attempts)
    }
}
