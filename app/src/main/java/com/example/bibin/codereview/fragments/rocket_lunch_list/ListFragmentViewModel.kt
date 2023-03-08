package com.example.bibin.codereview.fragments.rocket_lunch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibin.codereview.api.ApiResult
import com.example.bibin.codereview.coroutine.Coroutine
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.repo.master.MasterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @param masterRepo The repository responsible for providing the list of Rocket Lunches
 * EmployeesViewModel handling data operations and exposing data to the UI.
 */

class ListFragmentViewModel(private val masterRepo: MasterRepository) : ViewModel() {
    //job reference for the Coroutine job.
    private lateinit var job: Job

    //mRocketMutableFlow A MutableStateFlow instance to hold the result of the RocketLaunchModels list.
    private val mRocketMutableFlow = MutableStateFlow<List<RocketLaunchModels>>(emptyList())

    //mRocketList a StateFlow instance to expose the result of the RocketLaunchModels list to the UI.
    val mRocketList: StateFlow<List<RocketLaunchModels>> get() = mRocketMutableFlow.asStateFlow()

    /**
     * This method gets call when the view model is start.
     */
    init {
        mGetFetchList()
        mGetAllRocketList()
    }

    /**
     * It uses a Coroutine to get the data from the repository in a background thread
     * and then returns the result to the Main thread.
     * Then the result insert to the local db, uses a Coroutine to insert rocket launches RoomDb using a background thread
     */

    private fun mGetFetchList() {
           job = Coroutine.mIOThenMain({ masterRepo.mGetRocketListRemote() }, {
               when (it) {
                   is ApiResult.Exception -> {}
                   is ApiResult.OnFail -> {

                   }
                   is ApiResult.OnLoading -> {

                   }
                   is ApiResult.OnSuccess -> {
                       Coroutine.mIOperation { masterRepo.mInsertAllRocket(it.data) }
                   }
                   null -> {}
               }
           })

    }

    /**
     * It uses a Coroutine flow to get the data from the RoomDb in a background thread
     */
    private fun mGetAllRocketList() {
            job = viewModelScope.launch {
            masterRepo.mGetRocketListLocal().flowOn(Dispatchers.IO).catch { e ->
                throw Exception(e)
            }.collect {
                mRocketMutableFlow.value = it
            }
        }
    }

    /**
     * This method gets called when the ViewModel is no longer used and will be destroyed.
     * It cancels the Coroutine job if it is currently running.
     */
    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}