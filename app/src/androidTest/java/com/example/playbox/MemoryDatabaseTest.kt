package com.example.playbox

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.playbox.core.data.database.PlayBoxDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MemoryDatabaseTest {
    @Test fun bestRecordSurvivesReopenAndWorseResults() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val name = "memory-test-${System.nanoTime()}.db"
        fun open() = Room.databaseBuilder<PlayBoxDatabase>(
            context = context,
            name = context.getDatabasePath(name).absolutePath,
        ).setDriver(BundledSQLiteDriver()).setQueryCoroutineContext(Dispatchers.IO).build()
        try {
            val first = open()
            try {
                first.memoryRecordDao().saveIfBetter(8)
                first.memoryRecordDao().saveIfBetter(6)
                first.memoryRecordDao().saveIfBetter(10)
            } finally { first.close() }
            val reopened = open()
            try { assertEquals(6, reopened.memoryRecordDao().observeBestAttempts().first()) }
            finally { reopened.close() }
        } finally { context.deleteDatabase(name) }
    }
}
