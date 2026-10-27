package com.example.playbox.feature.memory.data.repository

import com.example.playbox.feature.memory.data.local.MemoryRecordDao
import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository

class RoomMemoryRecordRepository(private val dao: MemoryRecordDao) : MemoryRecordRepository {
    override fun observeBestAttempts() = dao.observeBestAttempts()
    override suspend fun saveIfBetter(attempts: Int) = dao.saveIfBetter(attempts)
}
