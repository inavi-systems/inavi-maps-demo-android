package com.inavi.maps.androiddemo.activity

import android.widget.CheckedTextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_map_option_gesture.*

class MapGestureActivity : InvMapFragmentActivity(R.layout.activity_map_option_gesture) {

  private val chkEnabledScroll: CheckedTextView by lazy {
    chk_gesture_enabled_scroll
  }

  private val chkEnabledZoom: CheckedTextView by lazy {
    chk_gesture_enabled_zoom
  }

  private val chkEnabledTilt: CheckedTextView by lazy {
    chk_gesture_enabled_tilt
  }

  private val chkEnabledRotate: CheckedTextView by lazy {
    chk_gesture_enabled_rotate
  }

  private val chkFocalCenter: CheckedTextView by lazy {
    chk_gesture_focal_center
  }

  private fun setCheckListener(chk: CheckedTextView, changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(chk.isChecked)

    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      changedState?.invoke(chk.isChecked)
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {

    setCheckListener(chkEnabledScroll) { isChecked ->
      inaviMap.uiSettings.isScrollGesturesEnabled = isChecked
    }

    setCheckListener(chkEnabledZoom) { isChecked ->
      inaviMap.uiSettings.isZoomGesturesEnabled = isChecked
    }

    setCheckListener(chkEnabledTilt) { isChecked ->
      inaviMap.uiSettings.isTiltGesturesEnabled = isChecked
    }

    setCheckListener(chkEnabledRotate) { isChecked ->
      inaviMap.uiSettings.isRotateGesturesEnabled = isChecked
    }

    setCheckListener(chkFocalCenter) { isChecked ->
      inaviMap.uiSettings.isFocalPointCenter = isChecked
    }
  }
}