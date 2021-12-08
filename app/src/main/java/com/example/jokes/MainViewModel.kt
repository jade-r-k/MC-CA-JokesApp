package com.example.jokes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jokes.api.RetrofitInstance
import com.example.jokes.data.AppDatabase
import com.example.jokes.data.JokeEntity
import com.example.jokes.data.SampleDataProvider
import com.example.jokes.models.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _jokes: MutableLiveData<List<Joke>> = MutableLiveData()
    val jokes: LiveData<List<Joke>>
    get() = _jokes

    private val database = AppDatabase.getInstance(app)

    val jokesList = database?.jokeDao()?.getAll()

    //Adds jokes from sample data
    fun randomJokes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleJokes = SampleDataProvider.getJokes()
                database?.jokeDao()?.insertAll(sampleJokes)
            }
        }
    }

    //When random jokes menu option is clicked, gets random jokes from API and inserts them into database
    fun getJokes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedJokes = RetrofitInstance.api.getJokes()
                Log.i(TAG, "Fetched jokes: $fetchedJokes")
                database?.jokeDao()?.insertAll(fetchedJokes.jokes as ArrayList<JokeEntity>)
            }
        }
    }

    //Deletes selected jokes
    fun deleteJokes(selectedJokes: List<JokeEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.jokeDao()?.deleteJokes(selectedJokes)
            }
        }
    }
}