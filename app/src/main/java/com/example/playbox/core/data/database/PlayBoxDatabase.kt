package com.example.playbox.core.data.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.playbox.feature.memory.data.local.MemoryRecordDao
import com.example.playbox.feature.memory.data.local.MemoryRecordEntity

@Database(entities = [MemoryRecordEntity::class], version = 1, exportSchema = true)
abstract class PlayBoxDatabase : RoomDatabase() {
    abstract fun memoryRecordDao(): MemoryRecordDao
}
