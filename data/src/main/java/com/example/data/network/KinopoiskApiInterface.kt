package com.example.data.network

import com.example.domain.models.FilmDetailsDto
import com.example.domain.models.FilmTopResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApiInterface {

    @GET("v2.2/films/top")
    suspend fun getTopFilms(@Query("type") type: String, @Query("page") page: Int): FilmTopResponse

    @GET("v2.2/films/{id}")
    suspend fun getTopFilmById(@Path("id") id: String): FilmDetailsDto
}