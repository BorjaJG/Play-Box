package com.example.playbox.feature.memory.domain.usecase

import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository

class ObserveMemoryRecord(private val repository: MemoryRecordRepository) {
    operator fun invoke() = repository.observeBestAttempts()
}
