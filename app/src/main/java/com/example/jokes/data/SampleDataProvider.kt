package com.example.jokes.data

import java.util.*

class SampleDataProvider {
    companion object {
        private val sampleText1 = "A simple note"
        private val sampleText2 = "I went to buy some camo pants\nbut couldn’t find any."
        private val sampleText3 =
            "Most people are shocked when they find out how bad I am as an electrician."

        private val sampleCategory = "Misc"

        fun getJokes() = arrayListOf(
            JokeEntity(0, sampleCategory, sampleText1),
            JokeEntity(1, sampleCategory, sampleText2),
            JokeEntity(2, sampleCategory, sampleText3)
        )

    }
}