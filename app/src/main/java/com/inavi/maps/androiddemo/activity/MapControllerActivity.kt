package com.inavi.maps.androiddemo.activity

import android.widget.CheckedTextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import kotlinx.android.synthetic.main.activity_map_option_controller.*

class MapControllerActivity : InvMapFragmentActivity(
  R.layout.activity_map_option_controller,
  InvMapOptions().zoomControlVisible(true).locationButtonVisible(true)) {

  private val chkCompass: CheckedTextView by lazy {
    chk_controller_visible_compass
  }

  private val chkScaleBar: CheckedTextView by lazy {
    chk_controller_visible_scale_bar
  }

  private val chkZoom: CheckedTextView by lazy {
    chk_controller_visible_zoom
  }

  private val chkCurLocation: CheckedTextView by lazy {
    chk_controller_visible_cur_location
  }

  private fun setCheckListener(chk: CheckedTextView, changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(chk.isChecked)

    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      changedState?.invoke(chk.isChecked)
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {
    inaviMap.moveCamera(CameraUpdate.bearingTo(45.0))

    setCheckListener(chkCompass) { isChecked ->
      inaviMap.uiSettings.isCompassVisible = isChecked
    }

    setCheckListener(chkScaleBar) { isChecked ->
      inaviMap.uiSettings.isScaleBarVisible = isChecked
    }

    setCheckListener(chkZoom) { isChecked ->
      inaviMap.uiSettings.isZoomControlVisible = isChecked
    }

    setCheckListener(chkCurLocation) { isChecked ->
      inaviMap.uiSettings.isLocationButtonVisible = isChecked
    }
  }
}