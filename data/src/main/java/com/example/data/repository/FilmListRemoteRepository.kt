package com.example.data.repository

import com.example.data.network.KinopoiskApiInterface
import com.example.domain.models.FilmItemDto
import com.example.domain.repository.FilmListRemoteRepository

class FilmListRemoteRepository(private val api: KinopoiskApiInterface) : FilmListRemoteRepository {

    override suspend fun getTopFilms(type: String, page: Int): List<FilmItemDto> {
        return api.getTopFilms(type, page).films
    }
}