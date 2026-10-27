package com.example.playbox.app.di

import com.example.playbox.feature.home.presentation.HomeViewModel
import com.example.playbox.feature.memory.data.local.MemoryRecordDao
import com.example.playbox.feature.memory.domain.model.MemoryGame
import com.example.playbox.feature.memory.domain.usecase.SaveMemoryRecord
import com.example.playbox.feature.memory.presentation.MemoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
class AppModulesTest {
    @Test fun productionGraphConnectsViewModelsToUseCasesRepositoriesAndDao() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        val dao = object : MemoryRecordDao {
            val best = MutableStateFlow<Int?>(null)
            override fun observeBestAttempts() = best
            override suspend fun saveIfBetter(attempts: Int) {
                best.value = minOf(best.value ?: Int.MAX_VALUE, attempts)
            }
        }
        // Replace only the platform boundary; use production modules above it.
        val app = koinApplication {
            allowOverride(true)
            modules(appModules)
            modules(module { single<MemoryRecordDao> { dao } })
        }
        try {
            val home = app.koin.get<HomeViewModel>()
            val memory = app.koin.get<MemoryViewModel>()
            assertEquals(listOf("memory"), home.state.value.games.map { it.id })
            runCurrent()
            val completed = MemoryGame(
                cards = listOf(0, 0, 1, 1, 2, 2, 3, 3), matched = (0..7).toSet(), attempts = 7,
            )
            app.koin.get<SaveMemoryRecord>()(completed)
            runCurrent()
            assertEquals(7, dao.best.value)
            assertEquals(7, memory.state.value.bestAttempts)
        } finally {
            app.close()
            Dispatchers.resetMain()
        }
    }
}
