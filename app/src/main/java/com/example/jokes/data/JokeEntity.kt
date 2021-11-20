package com.example.jokes.data

import com.example.jokes.NEW_JOKE_ID
import java.util.*

data class JokeEntity(
    var id: Int,
    var date: Date,
    var Category: String,
    var Joke: String
) {
    constructor() : this(NEW_JOKE_ID, Date(), "", "")
    constructor(date: Date, category: String, joke: String) : this(
        NEW_JOKE_ID,
        date,
        category,
        joke
    )
}