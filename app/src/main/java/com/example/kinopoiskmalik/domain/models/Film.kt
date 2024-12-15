package com.example.kinopoiskmalik.domain.models

import java.util.UUID

data class Film(
    val id: String,
    val name: String,
    val poster: Image,
    val genres: List<Genre>,
    val year: String,
) {
    companion object {
        fun generateMock(
            size: Int = 1,
        ): List<Film> {
            return List(size) {
                Film(
                    id = UUID.randomUUID().toString(),
                    name = "Interstellar",
                    poster = Image("https://example.com/poster/interstellar"),
                    genres = listOf(Genre("Sci-Fi"), Genre("Adventure")),
                    year = "2014",
                )
            }
        }
    }
}
