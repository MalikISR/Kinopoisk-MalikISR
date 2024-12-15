package com.example.kinopoiskmalik.data.network_layer.models


import androidx.annotation.Keep
import com.example.kinopoiskmalik.domain.models.Image
import com.example.kinopoiskmalik.domain.models.FilmDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class FilmDetailDto(
    @SerialName("kinopoiskId")
    val kinopoiskId: String,
    @SerialName("countries")
    val countries: List<CountryDto>?,
    @SerialName("description")
    val description: String?,
    @SerialName("genres")
    val genres: List<GenreDto>?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
)

fun FilmDetailDto.toFilmDetail() = FilmDetail(
    id = kinopoiskId,
    countries = countries?.map(CountryDto::toCountry)?: listOf(),
    description = description?: "",
    genres = genres?.map(GenreDto::toGenre) ?: listOf(),
    name = nameRu?: "",
    poster = Image(
        posterUrl?: "",
    )
)
