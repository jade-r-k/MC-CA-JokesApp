package com.example.jokes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: JokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(jokes: ArrayList<JokeEntity>)

    @Query("SELECT * FROM jokes ORDER BY id ASC")
    fun getAll(): LiveData<List<JokeEntity>>

    @Query("SELECT * FROM jokes WHERE id = :id")
    fun getJokeById(id: Int):JokeEntity?

    @Query("SELECT COUNT(*) FROM jokes")
    fun getCount(): Int

    @Delete
    fun deleteJokes(selectedJokes: List<JokeEntity>): Int
}