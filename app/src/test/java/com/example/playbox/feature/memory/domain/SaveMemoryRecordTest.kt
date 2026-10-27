package com.example.playbox.feature.memory.domain

import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository
import com.example.playbox.feature.memory.domain.usecase.SaveMemoryRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class SaveMemoryRecordTest {
    private class FakeRecords : MemoryRecordRepository {
        val best = MutableStateFlow<Int?>(null)
        override fun observeBestAttempts() = best
        override suspend fun saveIfBetter(attempts: Int) {
            best.value = minOf(best.value ?: Int.MAX_VALUE, attempts)
        }
    }
    @Test fun laterWorseResultDoesNotReplaceBest() = runTest {
        val repo = FakeRecords()
        val save = SaveMemoryRecord(repo)
        save(8); save(6); save(10)
        assertEquals(6, repo.best.value)
    }
    @Test fun impossibleCompletedResultIsRejected() = runTest {
        val repo = FakeRecords()
        try { SaveMemoryRecord(repo)(3); fail("Expected rejection") }
        catch (_: IllegalArgumentException) { assertNull(repo.best.value) }
    }
}
