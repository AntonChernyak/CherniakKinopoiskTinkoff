package com.example.domain.interactors

import com.example.domain.models.FilmDetailsDto
import com.example.domain.repository.FilmDetailsRemoteRepository

class FilmDetailsInteractor (
    private val remoteRepository: FilmDetailsRemoteRepository
        ){

    suspend fun getFilmDetails(id: String): FilmDetailsDto {
        return remoteRepository.getFilmDetailsById(id)
    }
}