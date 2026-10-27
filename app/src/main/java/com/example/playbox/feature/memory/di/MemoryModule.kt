package com.example.playbox.feature.memory.di

import com.example.playbox.feature.memory.data.repository.RoomMemoryRecordRepository
import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository
import com.example.playbox.feature.memory.domain.usecase.*
import com.example.playbox.feature.memory.presentation.MemoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val memoryModule = module {
    single<MemoryRecordRepository> { RoomMemoryRecordRepository(get()) }
    factory { ObserveMemoryRecord(get()) }
    factory { SaveMemoryRecord(get()) }
    factory { StartMemoryGame() }
    factory { RevealMemoryCard() }
    factory { ConcealMemoryCards() }
    viewModel { MemoryViewModel(get(), get(), get(), get(), get()) }
}
