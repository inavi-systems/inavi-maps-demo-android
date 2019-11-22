package com.inavi.maps.androiddemo.util

import android.content.res.Resources
import android.widget.CheckedTextView

fun Int.DpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.PxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun CheckedTextView.setCheckListener(changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(isChecked)
    setOnClickListener {
        isChecked = !isChecked
        changedState?.invoke(isChecked)
    }
}