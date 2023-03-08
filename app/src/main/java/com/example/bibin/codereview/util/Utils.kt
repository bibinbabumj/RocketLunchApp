package com.example.bibin.codereview.util

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.bibin.codereview.R

/**
 * mLoadText: a binding adapter used to set text and text color for a TextView based on whether a rocket lunch
 * is successful or upcoming.
 *@param view set text and text color
 */
@BindingAdapter("app:rocketLunch")
fun mLoadText(view: TextView, isAvailable: Boolean) {
    if (isAvailable) {
        view.text = view.context.getString(R.string.successful)
        view.setTextColor(Color.GREEN)
    } else {
        view.text = view.context.getString(R.string.upcoming)
        view.setTextColor(Color.RED)
    }

}

/**
 * extension function on View used to make a view visible.
 */
fun View.mShow() {
    visibility = View.VISIBLE
}

/**
 * extension function on View used to hide a view.
 */
fun View.mHide() {
    visibility = View.GONE
}

object Constants{
    const val ROCKET = "rocket"
}