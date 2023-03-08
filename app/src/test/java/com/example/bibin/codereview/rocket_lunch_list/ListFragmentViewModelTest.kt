package com.example.bibin.codereview.rocket_lunch_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.fragments.rocket_lunch_list.ListFragmentViewModel
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.repo.master.MasterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListFragmentViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var masterRepo: MasterRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getEmptyList() = runTest {
        Mockito.`when`(masterRepo.mGetRocketListRemote())
            .thenReturn(ApiResult.OnSuccess(emptyList()))
        val sut = ListFragmentViewModel(masterRepo)
        sut.mRocketList.onEach {
            assertEquals(0, it.size)
        }
    }

    @Test
    fun getRocketLunchListWithReturnData() = runTest {
        val mRocketList = mCreateData()
        Mockito.`when`(masterRepo.mGetRocketListRemote())
            .thenReturn(ApiResult.OnSuccess(mRocketList))
        val sut = ListFragmentViewModel(masterRepo)
        sut.mRocketList.onEach {
            assertEquals(5, it.size)
        }

    }

    private fun mCreateData() = listOf(
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
}