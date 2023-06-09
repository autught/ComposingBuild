package com.autught.chat.remote

import retrofit2.http.GET

interface ApiService {
    @GET("test")
    suspend fun request(): Nothing
}