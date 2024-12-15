package com.example.kinopoiskmalik.domain.repository

import com.example.kinopoiskmalik.domain.models.Film
import com.example.kinopoiskmalik.domain.models.FilmDetail
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilms(
        queryType: FilmQueryType,
        keyWord: String,
    ): Flow<List<Film>>


    fun getFilmDetail(
        id: String,
        force: Boolean,
    ): Flow<FilmDetail>

}

enum class FilmQueryType(val type: String) {
    TOP_100("TOP_100_POPULAR_FILMS")
}
