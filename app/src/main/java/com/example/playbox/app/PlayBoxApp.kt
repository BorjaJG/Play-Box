package com.example.playbox.app

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.playbox.app.di.AppContainer
import com.example.playbox.feature.home.presentation.HomeScreen
import com.example.playbox.feature.home.presentation.HomeViewModel
import com.example.playbox.feature.memory.presentation.MemoryScreen
import com.example.playbox.feature.memory.presentation.MemoryViewModel

@Composable
fun PlayBoxApp(container: AppContainer) {
    var selectedGame by rememberSaveable { mutableStateOf<String?>(null) }
    BackHandler(enabled = selectedGame != null) { selectedGame = null }
    Scaffold { padding ->
        Box(Modifier.padding(padding)) {
            if (selectedGame == "memory") {
                val vm: MemoryViewModel = viewModel(factory = viewModelFactory {
                    initializer { MemoryViewModel(container.memoryRecords) }
                })
                val state by vm.state.collectAsStateWithLifecycle()
                MemoryScreen(state, vm::reveal, vm::conceal, vm::restart, vm::persistResult, { selectedGame = null })
            } else {
                val vm: HomeViewModel = viewModel(factory = viewModelFactory {
                    initializer { HomeViewModel(container.gameCatalog) }
                })
                val state by vm.state.collectAsStateWithLifecycle()
                HomeScreen(state) { selectedGame = it }
            }
        }
    }
}
