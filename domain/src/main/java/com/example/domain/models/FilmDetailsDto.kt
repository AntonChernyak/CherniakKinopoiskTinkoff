package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FilmDetailsDto(
    @SerialName("posterUrl")
    val poster: String,
    @SerialName("nameRu")
    val title: String?,
    val description: String?,
    val countries: List<Country>,
    val genres: List<Genre>
)

@Serializable
class Country(
    val country: String
)

@Serializable
class Genre(
    val genre: String
)
