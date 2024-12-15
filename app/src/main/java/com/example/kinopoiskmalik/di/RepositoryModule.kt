package com.example.kinopoiskmalik.di

import com.example.kinopoiskmalik.data.repository.FilmRepositoryImpl
import com.example.kinopoiskmalik.domain.repository.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindFilmRepository(repo: FilmRepositoryImpl): FilmRepository
}
