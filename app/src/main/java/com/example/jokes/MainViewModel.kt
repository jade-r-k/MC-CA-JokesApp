package com.example.jokes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokes.data.AppDatabase
import com.example.jokes.data.JokeEntity
import com.example.jokes.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val jokesList = database?.jokeDao()?.getAll()

    fun randomJokes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleJokes = SampleDataProvider.getJokes()
                database?.jokeDao()?.insertAll(sampleJokes)
            }
        }
    }
}