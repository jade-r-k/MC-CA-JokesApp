package com.example.jokes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jokes.data.AppDatabase
import com.example.jokes.data.JokeDao
import com.example.jokes.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: JokeDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.jokeDao()!!
    }

    @Test
    fun createJokes() {
        dao.insertAll(SampleDataProvider.getJokes())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getJokes().size)
    }

    @After
    fun closeDb() {
        database.close()
    }
}