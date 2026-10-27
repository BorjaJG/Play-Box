package com.example.playbox.feature.memory.domain.repository

import kotlinx.coroutines.flow.Flow

interface MemoryRecordRepository {
    fun observeBestAttempts(): Flow<Int?>
    /** Atomically keeps the lowest completed-game attempt count. */
    suspend fun saveIfBetter(attempts: Int)
}
