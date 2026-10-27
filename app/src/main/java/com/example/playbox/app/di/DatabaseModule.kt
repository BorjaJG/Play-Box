package com.example.playbox.app.di

import com.example.playbox.app.data.database.PlayBoxDatabase
import com.example.playbox.core.data.database.createDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { createDatabase<PlayBoxDatabase>(androidContext(), "playbox.db") }
    single { get<PlayBoxDatabase>().memoryRecordDao() }
}
