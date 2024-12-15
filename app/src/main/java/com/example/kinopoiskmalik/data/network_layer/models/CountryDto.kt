package com.example.kinopoiskmalik.data.network_layer.models

import androidx.annotation.Keep
import com.example.kinopoiskmalik.domain.models.Country
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CountryDto(
    val country: String
)

fun CountryDto.toCountry() = Country(
    name = country,
)
