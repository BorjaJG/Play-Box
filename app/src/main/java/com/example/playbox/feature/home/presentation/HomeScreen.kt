package com.example.playbox.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(state: HomeUiState, onGameSelected: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("PLAY BOX", style = MaterialTheme.typography.labelLarge)
            Text("Un rato para jugar", style = MaterialTheme.typography.headlineLarge)
            Text("Juegos pequeños. Retos para superarte.")
        }
        items(state.games, key = { it.id }) { game ->
            Card(onClick = { onGameSelected(game.id) }) {
                Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(game.title, style = MaterialTheme.typography.titleLarge)
                    Text(game.description)
                    Text("Jugar →", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
