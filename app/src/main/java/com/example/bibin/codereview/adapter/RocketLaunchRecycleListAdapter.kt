package com.example.bibin.codereview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.databinding.AdapterListItemViewBinding

/**
 * ListAdapter class for the Recycler view that displays a list of [RocketLaunchModels] objects.
 */
class RocketLaunchRecycleListAdapter(private var onClickListener: OnClickListener) :
    RecyclerView.Adapter<RocketLaunchRecycleListAdapter.ListItemViewHolder>() {

    // List of Rocket Lunches to be displayed
    private var rocketLaunchList: List<RocketLaunchModels> = emptyList()

    /**
     * public method that sets the list of Rocket Lunch and updates the adapter.notify the adapter
     */
    @SuppressLint("NotifyDataSetChanged")
    fun mSetRocketList(rocketLaunchList: List<RocketLaunchModels>) {
        this.rocketLaunchList = rocketLaunchList
        notifyDataSetChanged()
    }

    /**
     * onclickItem, it pass the data to fragment with the help of onClickItem function
     */
    interface OnClickListener {
        fun onClickItem(rocketLaunch: RocketLaunchModels)
    }

    /**
     * create a new [ListItemViewHolder] instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
        ListItemViewHolder(
            AdapterListItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    /**
     * To bind the data [RocketLaunchModels] at a particular position to the [ListItemViewHolder].
     */

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener.onClickItem(rocketLaunchList[position])
        }
        rocketLaunchList[position].let {
            holder.mBindData(it)
        }
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemId(position: Int): Long = position.toLong()

    /**
     * method that returns the number of items in the adapter
     */
    override fun getItemCount(): Int = rocketLaunchList.size

    inner class ListItemViewHolder(private val binding: AdapterListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         *  binds an Rocket Lunch object to the [ListItemViewHolder]
         */
        fun mBindData(rocketLaunch: RocketLaunchModels) {
            binding.rocketmodel=rocketLaunch
            binding.executePendingBindings()
        }

    }


}


