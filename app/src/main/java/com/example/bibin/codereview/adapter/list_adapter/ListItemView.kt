package com.example.bibin.codereview.adapter.list_adapter

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.bibin.codereview.model.RocketLaunchModels
import com.example.bibin.codereview.R

/**
 * Custom view to show a [RocketLaunchModels] in the list in the list fragment.
 */
class ListItemView(context: Context) : CardView(context) {

    private val missionNameText: TextView
    private val launchSuccessText: TextView

    init {
        inflate(context, R.layout.adapter_list_item_view, this)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.marginStart = 40
        params.marginEnd = 40
        layoutParams = params
        missionNameText = findViewById(R.id.missionNameText)
        launchSuccessText = findViewById(R.id.launchSuccessText)
    }

    fun setItem(listItem: RocketLaunchModels) {
        missionNameText.text = listItem.name
        if (listItem.success) {
            launchSuccessText.text = context.getString(R.string.successful)
            launchSuccessText.setTextColor(Color.GREEN)
        } else {
            launchSuccessText.text = context.getString(R.string.unsuccessful)
            launchSuccessText.setTextColor(Color.RED)
        }
    }
}
