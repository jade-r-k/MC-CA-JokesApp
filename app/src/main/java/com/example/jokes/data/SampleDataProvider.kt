package com.example.jokes.data

//Sample data for database

import java.util.*

class SampleDataProvider {
    companion object {
        private val sampleText1 = "A simple note"
        private val sampleText2 = "I went to buy some camo pants\nbut couldn’t find any."
        private val sampleText3 =
            "Most people are shocked when they find out how bad I am as an electrician."

        private val sampleCategory = "Misc"

        fun getJokes() = arrayListOf(
            JokeEntity(sampleCategory, sampleText1),
            JokeEntity(sampleCategory, sampleText2),
            JokeEntity(sampleCategory, sampleText3)
        )

    }
}