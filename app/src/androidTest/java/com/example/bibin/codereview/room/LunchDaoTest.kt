package com.example.bibin.codereview.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.bibin.codereview.model.RocketLaunchModels
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var launchDao: LaunchDao
    private lateinit var database: AppDatabase


    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).allowMainThreadQueries().build()
        launchDao = database.dao()

    }

    @Test
    fun insert_multiple_rocket_lunch_excepted_result() = runBlocking {

        val list = listOf(
            RocketLaunchModels(
                1, "FalconSat1", "2006-03-24T22:30:00.000Z",
                "Engine failure at 33 seconds and loss of vehicle", false
            ),
            RocketLaunchModels(
                2, "FalconSat2", "2006-03-24T22:30:00.000Z",
                "Engine failure at 33 seconds and loss of vehicle", false
            ),
            RocketLaunchModels(
                3, "FalconSat3", "2006-03-24T22:30:00.000Z",
                "Engine failure at 33 seconds and loss of vehicle", false
            )
        )
        launchDao.insertAll(list)

        launchDao.getAllLaunches().test {
            val data = awaitItem()
            assertEquals(3, data.size)
            cancel()

        }

    }

    @Test
    fun getEmptyList() = runBlocking {
        launchDao.getAllLaunches().test {
            val data = awaitItem()
            assertEquals(0, data.size)
            cancel()

        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}