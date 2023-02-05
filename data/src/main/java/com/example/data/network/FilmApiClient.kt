package com.example.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object FilmApiClient {

    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
    private val contentType = "application/json".toMediaType()
    private val json by lazy { Json { ignoreUnknownKeys = true } }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .authenticator(FilmAuthenticator())
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    val apiClient: KinopoiskApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return@lazy retrofit.create(KinopoiskApiInterface::class.java)
    }
}