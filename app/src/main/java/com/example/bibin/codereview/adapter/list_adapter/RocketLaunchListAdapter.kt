package com.example.bibin.codereview.adapter.list_adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bibin.codereview.model.RocketLaunchModels

/**
 * ListAdapter class for the Recycler view that displays a list of [RocketLaunchModels] objects.
 */
class RocketLaunchListAdapter internal constructor(private val context: Context,private var onClickListListener: OnClickListListener) :
    ListAdapter<RocketLaunchModels, RocketLaunchListAdapter.ListItemViewHolder>(
        RocketLaunchListDiffCallback()
    ) {

    inner class ListItemViewHolder(val listItemView: ListItemView) :
        RecyclerView.ViewHolder(listItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ListItemView(context))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.listItemView.setItem(item)
        holder.listItemView.setOnClickListener {
            onClickListListener.onClickListItem(item)
        }
    }
    interface OnClickListListener {
        fun onClickListItem(rocketLaunch: RocketLaunchModels)
    }
}
