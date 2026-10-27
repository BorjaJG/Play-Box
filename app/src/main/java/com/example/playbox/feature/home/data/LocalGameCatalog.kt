package com.example.playbox.feature.home.data

import com.example.playbox.core.domain.model.GameDefinition
import com.example.playbox.feature.home.domain.repository.GameCatalog

class LocalGameCatalog : GameCatalog {
    override fun games() = listOf(
        GameDefinition("memory", "Memoria", "Encuentra las cuatro parejas en el menor número de intentos."),
    )
}
