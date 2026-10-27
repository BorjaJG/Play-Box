package com.example.playbox.feature.home.domain.repository

import com.example.playbox.core.domain.model.GameDefinition

interface GameRepository { fun getGames(): List<GameDefinition> }
