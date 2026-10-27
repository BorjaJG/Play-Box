package com.example.playbox.feature.memory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MemoryScreen(
    state: MemoryUiState,
    onReveal: (Int) -> Unit,
    onConceal: () -> Unit,
    onRestart: () -> Unit,
    onRetrySave: () -> Unit,
    onBack: () -> Unit,
) {
    val game = state.game
    val symbols = listOf("Sol", "Luna", "Mar", "Flor")
    Column(
        Modifier.verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextButton(onClick = onBack) { Text("← Juegos") }
        Text("Memoria", style = MaterialTheme.typography.headlineLarge)
        Text("Encuentra las 4 parejas. Cada dos cartas cuentan como un intento.")
        Text("Intentos: ${game.attempts} · Récord: ${state.bestAttempts ?: "—"}")
        repeat(4) { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(2) { column ->
                    val index = row * 2 + column
                    val visible = index in game.revealed || index in game.matched
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        enabled = index !in game.matched && index !in game.revealed && !game.needsConceal,
                        onClick = { onReveal(index) },
                    ) {
                        Text(if (visible) symbols[game.cards[index]] else "Carta ${index + 1}")
                    }
                }
            }
        }
        if (game.needsConceal) Button(onClick = onConceal) { Text("Seguir jugando") }
        if (game.isFinished) {
            Text("¡Las cuatro parejas!", style = MaterialTheme.typography.titleLarge)
            if (state.saving) Text("Guardando resultado…")
        }
        state.error?.let { message ->
            Text(message, color = MaterialTheme.colorScheme.error)
            if (game.isFinished && !state.saving) TextButton(onClick = onRetrySave) { Text("Reintentar guardado") }
        }
        TextButton(onClick = onRestart, enabled = !state.saving) { Text("Nueva partida") }
    }
}
