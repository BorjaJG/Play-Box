package com.example.playbox.feature.memory.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "memory_records")
data class MemoryRecordEntity(@PrimaryKey val id: Int = 1, val bestAttempts: Int)
