package com.example.data.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class FilmAuthenticator : Authenticator{

    override fun authenticate(route: Route?, response: Response): Request {

        val token = "575c2ec4-41a6-4956-be9d-17a820dacf30"

        return response.request.newBuilder()
            .removeHeader((TOKEN_KEY))
            .addHeader(TOKEN_KEY, token)
            .build()
    }

    companion object {
        private const val TOKEN_KEY = "X-API-KEY"
    }

}