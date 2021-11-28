package com.example.jokes.api

import com.example.jokes.models.Joke
import retrofit2.http.GET

interface JokeApi {
    @GET("Any?amount=5&lang=en&safe-mode&type=single")
    suspend fun getJokes(): List<Joke>
}