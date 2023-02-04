package com.example.data.repository

import com.example.data.network.KinopoiskApiInterface
import com.example.domain.models.FilmDetailsDto
import com.example.domain.repository.FilmDetailsRemoteRepository

class FilmDetailsRemoteRepository(private val api: KinopoiskApiInterface) : FilmDetailsRemoteRepository {

    override suspend fun getFilmDetailsById(id: String): FilmDetailsDto {
        return api.getTopFilmById(id)
    }
}