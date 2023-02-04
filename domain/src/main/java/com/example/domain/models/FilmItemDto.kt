package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FilmItemDto(
    @SerialName("kinopoiskId")
    val id: Int,
    @SerialName("nameRu")
    val title: String?,
    @SerialName("posterUrlPreview")
    val poster: String,
    val year: Int?
)