package com.example.jokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokes.data.JokeEntity
import com.example.jokes.data.SampleDataProvider

class MainViewModel : ViewModel() {
    val notesList = MutableLiveData<List<JokeEntity>>()

    init {
        notesList.value = SampleDataProvider.getJokes()
    }
}