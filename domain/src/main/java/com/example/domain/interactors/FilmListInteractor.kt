package com.example.domain.interactors

import com.example.domain.models.FilmItemDto
import com.example.domain.repository.FilmListRemoteRepository

class FilmListInteractor(
    private val remoteRepository: FilmListRemoteRepository
) {

    suspend fun getTopFilms(type: String, page: Int): List<FilmItemDto> {
       // val topsList  = mutableListOf<FilmItemDto>()
        return remoteRepository.getTopFilms(type, page)
    }
}