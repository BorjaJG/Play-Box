package com.example.playbox.app.di

import com.example.playbox.feature.home.di.homeModule
import com.example.playbox.feature.memory.di.memoryModule

val appModules = listOf(databaseModule, homeModule, memoryModule)
