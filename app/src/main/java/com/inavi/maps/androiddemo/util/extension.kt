package com.inavi.maps.androiddemo.util

import android.content.res.Resources

fun Int.DpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.PxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()