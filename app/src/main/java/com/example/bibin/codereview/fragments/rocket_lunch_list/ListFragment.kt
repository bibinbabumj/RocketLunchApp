package com.example.bibin.codereview.fragments.rocket_lunch_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderus.codingchallenge.adapter.list_adapter.ListItemDecoration
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.repo.master.MasterRepository
import com.example.bibin.codereview.room.AppDatabase
import com.example.bibin.codereview.util.Constants
import com.example.bibin.codereview.util.mHide
import com.example.bibin.codereview.util.mShow
import com.example.bibin.codereview.R
import com.example.bibin.codereview.adapter.RocketLaunchRecycleListAdapter
import com.example.bibin.codereview.adapter.list_adapter.RocketLaunchListAdapter
import com.example.bibin.codereview.api.Api
import com.example.bibin.codereview.api.RetrofitProvider
import com.example.bibin.codereview.databinding.FragmentListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Fragment to display the list of Rocket Launches.
 */
class ListFragment : Fragment(), RocketLaunchRecycleListAdapter.OnClickListener,
    RocketLaunchListAdapter.OnClickListListener {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    // Declare repository, viewModel factory, viewModel, api, adapter
    private lateinit var api: Api
    private lateinit var database: AppDatabase
    private lateinit var masterRepository: MasterRepository
    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var viewFactory: ListFragmentViewFactory
    private lateinit var adapter: RocketLaunchRecycleListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Initializes API, ViewModel ,ViewModel Factory, Master Repository
     */
    private fun mInit() {
        api = RetrofitProvider.invoke()
        database = AppDatabase.invoke(context!!.applicationContext)
        masterRepository = MasterRepository(api, database)
        viewFactory = ListFragmentViewFactory(masterRepository)
        viewModel = ViewModelProvider(this, viewFactory)[ListFragmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.mShow()
        // mInitializeRecycleAdapter()
        mObserveDataFromLocalDb()
    }

    /**
     * Initializes RecycleView Adapter
     */

    private fun mInitializeRecycleAdapter() {
        adapter = RocketLaunchRecycleListAdapter(this)
        binding.rocketLaunchList.adapter = adapter
        binding.rocketLaunchList.layoutManager = LinearLayoutManager(context)
        binding.rocketLaunchList.setHasFixedSize(true)
    }

    /**
     *
     * Observe the Rocket Lunch List in the ViewModel and Retrieve list of RocketLunches List from the viewModel.
     * Observe the Local Db using FLow
     * Here we can set List in Recyclerview Adapter or List Adapter
     * Recycle view Adapter-> uncomment mInitializeRecycleAdapter(),mSetDataToRecycleAdapter(data) and Comment  mSetDataWithListAdapter(data)
     * Recycle view Adapter-> uncomment  mSetDataWithListAdapter(data) and Comment mInitializeRecycleAdapter(),mSetDataToRecycleAdapter(data)
     */
    private fun mObserveDataFromLocalDb() {
        viewModel.mRocketList.onEach { data ->
            //mSetDataToRecycleAdapter(data)
            mSetDataWithListAdapter(data)
            binding.progressBar.mHide()
        }.launchIn(lifecycleScope)

    }

    /**
     * send list of Rocket Lunch to the Recycle Adapter
     */
    private fun mSetDataToRecycleAdapter(data: List<RocketLaunchModels>) {
        adapter.mSetRocketList(data)
    }

    /**
     * Handle click on Rocket Lunch item by launching [DetailFragment]
     * From RecycleView Adapter
     */
    override fun onClickItem(rocketLaunch: RocketLaunchModels) {
        mMoveToDetailsPage(rocketLaunch)
    }

    /**
     *  launching [DetailFragment] and pass Data [RocketLaunchModels]
     */
    private fun mMoveToDetailsPage(rocketLaunch: RocketLaunchModels) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.ROCKET, rocketLaunch)
        findNavController().navigate(R.id.action_rocket_listFragment_to_rocket_details, bundle)
    }

    /**
     * Initializes RecycleView Adapter
     * send list of Rocket Lunch to the List Adapter
     *
     */
    private fun mSetDataWithListAdapter(data: List<RocketLaunchModels>) {
        val mListAdapter = RocketLaunchListAdapter(context!!.applicationContext, this)
        mListAdapter.submitList(data)
        binding.rocketLaunchList.adapter = mListAdapter
        binding.rocketLaunchList.addItemDecoration(ListItemDecoration(20))

    }

    /**
     * Handle click on Rocket Lunch item by launching [DetailFragment]
     * From List Adapter
     */
    override fun onClickListItem(rocketLaunch: RocketLaunchModels) {
        mMoveToDetailsPage(rocketLaunch)
    }
}
