package com.example.jokes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: JokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(jokes: List<JokeEntity>)

    @Query("SELECT * FROM jokes ORDER BY id ASC")
    fun getAll(): LiveData<List<JokeEntity>>

    @Query("SELECT * FROM jokes WHERE id = :id")
    fun getJokeById(id: Int):JokeEntity?
}