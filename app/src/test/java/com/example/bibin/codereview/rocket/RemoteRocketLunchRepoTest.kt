package com.example.bibin.codereview.rocket

import com.example.bibin.codereview.api.Api
import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.repo.rocket.RemoteRocketLunchRepo
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class RemoteRocketLunchRepoTest {
    @Mock
    lateinit var api: Api

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getEmptyRocketLunchList() = runTest {
        Mockito.`when`(api.getRocketLaunchList()).thenReturn(Response.success(emptyList()))
        val sut = RemoteRocketLunchRepo(api)
        val result = sut.mGetRocketListRemote()
        assertEquals(true, result is ApiResult.OnSuccess)
        if (result is ApiResult.OnSuccess) {
            result.data
            assertEquals(0, result.data.size)
        }
    }

    @Test
    fun getRocketLunchListWithReturnData() = runTest {
        val mRocketList = mCreateData()
        Mockito.`when`(api.getRocketLaunchList()).thenReturn(Response.success(mRocketList))
        val sut = RemoteRocketLunchRepo(api)
        val result = sut.mGetRocketListRemote()
        assertEquals(true, result is ApiResult.OnSuccess)
        if (result is ApiResult.OnSuccess) {
            assertEquals(3, result.data.size)
            assertEquals("FalconSat1", result.data[0].name)
            assertEquals("Engine failure at 33 seconds and loss of vehicle", result.data[0].details)
        }

    }

    @Test
    fun getExpectedError() = runTest {
        Mockito.`when`(api.getRocketLaunchList())
            .thenReturn(Response.error(401, "Response.error()".toResponseBody()))
        val sut = RemoteRocketLunchRepo(api)
        val result = sut.mGetRocketListRemote()
        assertEquals(true, result is ApiResult.OnFail)
        if (result is ApiResult.OnFail) {
            assertEquals(401, result.errorCode)
            assertEquals("Response.error()", result.message)
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

@After
fun tearDown() {

}