package com.example.playbox.feature.home.domain.repository

import com.example.playbox.core.domain.model.GameDefinition

interface GameCatalog { fun games(): List<GameDefinition> }
