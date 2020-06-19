package com.example.trivialquiz.repository

import com.example.trivialquiz.model.JokesList
import retrofit2.http.GET
import retrofit2.Call

interface JokesApi {
    @GET("programming/ten")
    fun getJokes(): Call<JokesList>
}