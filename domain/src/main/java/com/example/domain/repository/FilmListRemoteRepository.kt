package com.example.domain.repository

import com.example.domain.models.FilmItemDto

interface FilmListRemoteRepository {

    suspend fun getTopFilms(type: String, page: Int): List<FilmItemDto>
}