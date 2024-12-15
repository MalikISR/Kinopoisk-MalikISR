package com.example.kinopoiskmalik.data.repository

import com.example.kinopoiskmalik.data.network_layer.KinopoiskApi
import com.example.kinopoiskmalik.data.network_layer.models.toFilm
import com.example.kinopoiskmalik.data.network_layer.models.toFilmDetail
import com.example.kinopoiskmalik.di.IoDispatcher

import com.example.kinopoiskmalik.domain.repository.FilmQueryType
import com.example.kinopoiskmalik.domain.repository.FilmRepository
import com.example.kinopoiskmalik.domain.models.Film
import com.example.kinopoiskmalik.domain.models.FilmDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FilmRepository {
    override fun getFilms(
        queryType: FilmQueryType,
        keyWord: String,
    ): Flow<List<Film>> = flow {
        val filmsDto = api.getFilms(queryType.type)
        val film = filmsDto.films
            .filter { filmDto ->
                filmDto.nameRu?.contains(
                    other = keyWord,
                    ignoreCase = true
                ) == true
        }.map { filmDto ->
            filmDto.toFilm()
        }

        emit(film)
    }.flowOn(ioDispatcher)

    override fun getFilmDetail(id: String, force: Boolean): Flow<FilmDetail> = flow {
        val filmDetailDto = api.getFilmById(id)
        emit(filmDetailDto.toFilmDetail())
    }.flowOn(ioDispatcher)

}
