package com.example.bibin.codereview.fragments.rocket_lunch_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.util.Constants
import com.example.bibin.codereview.databinding.FragmentDetailBinding

/**
 * A Fragment to display details of specific launches.
 */
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSetRocketData()


    }

    /**
     * It retrieves the rocket lunches[RocketLaunchModels] data from the bundle arguments and binds it to the UI using data binding.
     **/
    private fun mSetRocketData() {
        val data = arguments?.getSerializable(Constants.ROCKET) as RocketLaunchModels
        data.let {
            _binding?.rocketModel = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}