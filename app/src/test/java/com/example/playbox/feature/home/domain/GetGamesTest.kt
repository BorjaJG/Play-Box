package com.example.playbox.feature.home.domain

import com.example.playbox.core.domain.model.GameDefinition
import com.example.playbox.feature.home.domain.repository.GameRepository
import com.example.playbox.feature.home.domain.usecase.GetGames
import com.example.playbox.feature.home.presentation.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class GetGamesTest {
    @Test fun homeUsesCatalogProvidedByRepositoryIncludingEmptyCatalog() {
        var catalog = listOf(GameDefinition("test-game", "Otro juego", "Prueba"))
        val repository = object : GameRepository {
            override fun getGames() = catalog
        }
        val getGames = GetGames(repository)
        assertEquals("test-game", HomeViewModel(getGames).state.value.games.single().id)
        catalog = emptyList()
        assertEquals(emptyList<GameDefinition>(), HomeViewModel(getGames).state.value.games)
    }
}
