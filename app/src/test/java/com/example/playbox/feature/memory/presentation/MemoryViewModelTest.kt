package com.example.playbox.feature.memory.presentation

import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class MemoryViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    @Before fun setUp() { Dispatchers.setMain(dispatcher) }
    @After fun tearDown() { Dispatchers.resetMain() }
    private class Records(var fail: Boolean = false) : MemoryRecordRepository {
        val best = MutableStateFlow<Int?>(null)
        var writes = 0
        override fun observeBestAttempts() = best
        override suspend fun saveIfBetter(attempts: Int) {
            writes++
            if (fail) throw IllegalStateException("Storage unavailable")
            best.value = minOf(best.value ?: Int.MAX_VALUE, attempts)
        }
    }
    private fun finish(vm: MemoryViewModel) {
        vm.state.value.game.cards.indices.groupBy { vm.state.value.game.cards[it] }
            .values.forEach { pair -> pair.forEach(vm::reveal) }
    }
    @Test fun completionSavesOnceAndObservesRecord() = runTest(dispatcher) {
        val records = Records()
        val vm = MemoryViewModel(records)
        runCurrent()
        finish(vm)
        vm.reveal(0)
        runCurrent()
        assertEquals(1, records.writes)
        assertEquals(4, vm.state.value.bestAttempts)
        assertFalse(vm.state.value.saving)
    }
    @Test fun failedSaveCanBeRetriedWithoutLosingFinishedGame() = runTest(dispatcher) {
        val records = Records(fail = true)
        val vm = MemoryViewModel(records)
        runCurrent()
        finish(vm)
        runCurrent()
        assertNotNull(vm.state.value.error)
        assertTrue(vm.state.value.game.isFinished)
        records.fail = false
        vm.persistResult()
        runCurrent()
        assertNull(vm.state.value.error)
        assertEquals(4, vm.state.value.bestAttempts)
    }
}
