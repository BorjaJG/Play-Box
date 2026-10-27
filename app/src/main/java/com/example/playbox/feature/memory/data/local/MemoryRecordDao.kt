package com.example.playbox.feature.memory.data.local

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryRecordDao {
    @Query("SELECT bestAttempts FROM memory_records WHERE id = 1")
    fun observeBestAttempts(): Flow<Int?>

    @Query("INSERT INTO memory_records (id, bestAttempts) VALUES (1, :attempts) ON CONFLICT(id) DO UPDATE SET bestAttempts = MIN(bestAttempts, excluded.bestAttempts)")
    suspend fun saveIfBetter(attempts: Int)
}
