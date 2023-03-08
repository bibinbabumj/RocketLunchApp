package com.example.bibin.codereview.fragments.rocket_lunch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bibin.codereview.repo.master.MasterRepository

/**

*A factory class that is responsible for creating instances of [ListFragmentViewModel].
*/
@Suppress("UNCHECKED_CAST")
class ListFragmentViewFactory(private val masterRepo: MasterRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListFragmentViewModel(masterRepo) as T
    }
}