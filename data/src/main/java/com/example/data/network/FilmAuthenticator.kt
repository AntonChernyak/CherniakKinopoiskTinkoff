package com.example.data.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class FilmAuthenticator : Authenticator{

    override fun authenticate(route: Route?, response: Response): Request {

        val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

        return response.request.newBuilder()
            .removeHeader((TOKEN_KEY))
            .addHeader(TOKEN_KEY, token)
            .build()
    }

    companion object {
        private const val TOKEN_KEY = "X-API-KEY"
    }

}