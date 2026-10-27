package com.example.playbox.core.di

import android.content.Context
import com.example.playbox.core.data.database.createDatabase
import com.example.playbox.feature.home.data.LocalGameCatalog
import com.example.playbox.feature.memory.data.repository.RoomMemoryRecordRepository
import com.example.playbox.feature.memory.domain.repository.MemoryRecordRepository

/** Composition root: dependencies live for the Application lifetime. */
class AppContainer(context: Context) {
    private val database by lazy { createDatabase(context) }
    val gameCatalog = LocalGameCatalog()
    val memoryRecords: MemoryRecordRepository by lazy {
        RoomMemoryRecordRepository(database.memoryRecordDao())
    }
}
