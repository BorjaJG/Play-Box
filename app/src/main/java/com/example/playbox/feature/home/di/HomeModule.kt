package com.example.playbox.feature.home.di

import com.example.playbox.feature.home.data.LocalGameRepository
import com.example.playbox.feature.home.domain.repository.GameRepository
import com.example.playbox.feature.home.domain.usecase.GetGames
import com.example.playbox.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<GameRepository> { LocalGameRepository() }
    factory { GetGames(get()) }
    viewModel { HomeViewModel(get()) }
}
