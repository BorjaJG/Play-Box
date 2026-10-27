package com.example.playbox.core.data.database

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun createDatabase(context: Context): PlayBoxDatabase =
    Room.databaseBuilder<PlayBoxDatabase>(
        context = context.applicationContext,
        name = context.applicationContext.getDatabasePath("playbox.db").absolutePath,
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
