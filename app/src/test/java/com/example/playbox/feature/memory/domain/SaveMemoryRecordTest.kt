package com.example.playbox.feature.memory.domain

import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository
import com.example.playbox.feature.memory.domain.model.MemoryGame
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
    private fun completed(attempts: Int) = MemoryGame(
        cards = listOf(0, 0, 1, 1, 2, 2, 3, 3), matched = (0..7).toSet(), attempts = attempts,
    )
    @Test fun unfinishedGameCannotSaveEvenWithEnoughAttempts() = runTest {
        val repo = FakeRecords()
        try { SaveMemoryRecord(repo)(MemoryGame.newGame().copy(attempts = 8)); fail("Expected rejection") }
        catch (_: IllegalArgumentException) { assertNull(repo.best.value) }
    }
    @Test fun laterWorseResultDoesNotReplaceBest() = runTest {
        val repo = FakeRecords()
        val save = SaveMemoryRecord(repo)
        save(completed(8)); save(completed(6)); save(completed(10))
        assertEquals(6, repo.best.value)
    }
    @Test fun impossibleCompletedResultIsRejected() = runTest {
        val repo = FakeRecords()
        try { SaveMemoryRecord(repo)(completed(3)); fail("Expected rejection") }
        catch (_: IllegalArgumentException) { assertNull(repo.best.value) }
    }
}
