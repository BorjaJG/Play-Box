package com.example.playbox.feature.home.data

import com.example.playbox.core.domain.model.GameDefinition
import com.example.playbox.feature.home.domain.repository.GameRepository

class LocalGameRepository : GameRepository {
    override fun getGames() = listOf(
        GameDefinition("memory", "Memoria", "Encuentra las cuatro parejas en el menor número de intentos."),
    )
}
