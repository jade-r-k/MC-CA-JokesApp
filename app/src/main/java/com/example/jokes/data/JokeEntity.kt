package com.example.jokes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jokes.NEW_JOKE_ID
import java.util.*

@Entity(tableName = "jokes")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var category: String,
    var joke: String
) {
    constructor() : this(NEW_JOKE_ID, "", "")
    constructor(category: String, joke: String) : this(
        NEW_JOKE_ID,
        category,
        joke
    )
}