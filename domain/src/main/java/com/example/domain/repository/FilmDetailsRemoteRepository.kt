package com.example.domain.repository

import com.example.domain.models.FilmDetailsDto

interface FilmDetailsRemoteRepository {

    suspend fun getFilmDetailsById(id: String): FilmDetailsDto
}