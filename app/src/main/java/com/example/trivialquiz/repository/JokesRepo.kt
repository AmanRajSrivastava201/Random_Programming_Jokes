package com.example.trivialquiz.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesRepo {

    fun getQuizApi(): JokesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com/jokes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(JokesApi::class.java)
    }

}