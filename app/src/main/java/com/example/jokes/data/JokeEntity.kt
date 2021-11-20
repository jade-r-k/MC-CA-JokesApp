package com.example.jokes.data

import com.example.jokes.NEW_JOKE_ID
import java.util.*

data class JokeEntity(
    var id: Int,
    var Category: String,
    var Joke: String
) {
    constructor() : this(NEW_JOKE_ID, "", "")
    constructor(category: String, joke: String) : this(NEW_JOKE_ID, category, joke)
}