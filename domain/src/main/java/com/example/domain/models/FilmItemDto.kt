package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// FilmTopResponse_films
@Serializable
class FilmItemDto(
    @SerialName("filmId")
    val id: Int,
    @SerialName("nameRu")
    val title: String?,
    @SerialName("posterUrl")
    val poster: String,
    val year: String?
)