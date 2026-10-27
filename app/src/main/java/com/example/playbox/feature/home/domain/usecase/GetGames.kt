package com.example.playbox.feature.home.domain.usecase

import com.example.playbox.feature.home.domain.repository.GameRepository

class GetGames(private val repository: GameRepository) {
    operator fun invoke() = repository.getGames()
}
