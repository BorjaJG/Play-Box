package com.example.playbox.core.data.database

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

inline fun <reified T : RoomDatabase> createDatabase(context: Context, name: String): T =
    Room.databaseBuilder<T>(
        context = context.applicationContext,
        name = context.applicationContext.getDatabasePath(name).absolutePath,
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
