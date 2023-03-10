package com.coderus.codingchallenge.adapter.list_adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom RecyclerView ItemDecoration class to define spacing between RecyclerView items.
 */
class ListItemDecoration(private val verticalSpacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalSpacing
        outRect.bottom = verticalSpacing
    }
}

