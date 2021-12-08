package com.example.jokes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jokes.data.AppDatabase
import com.example.jokes.data.JokeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(app: Application) : AndroidViewModel(app) {
   private val database = AppDatabase.getInstance(app)
    val currentJoke = MutableLiveData<JokeEntity>()

    //Gets joke by id and displays selected joke
    fun getJokeById(jokeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val joke =
                    if (jokeId != NEW_JOKE_ID) {
                        database?.jokeDao()?.getJokeById(jokeId)
                    }else {
                        JokeEntity()
                    }
                currentJoke.postValue(joke!!)
            }
        }

    }
}