package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
class FilmTopResponse(
    val pagesCount: Int,
    val films: List<FilmItemDto>
)