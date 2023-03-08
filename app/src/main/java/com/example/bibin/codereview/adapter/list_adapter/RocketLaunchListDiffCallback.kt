package com.example.bibin.codereview.adapter.list_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.bibin.codereview.model.RocketLaunchModels

/**
 * [DiffUtil.ItemCallback] for the [RocketLaunchModels] list.
 */
class RocketLaunchListDiffCallback : DiffUtil.ItemCallback<RocketLaunchModels>() {

    override fun areItemsTheSame(oldItem: RocketLaunchModels, newItem: RocketLaunchModels) =
        oldItem.flightNumber == newItem.flightNumber

    override fun areContentsTheSame(oldItem: RocketLaunchModels, newItem: RocketLaunchModels) =
        oldItem == newItem

}
