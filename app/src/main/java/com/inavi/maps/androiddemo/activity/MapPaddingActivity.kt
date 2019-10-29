package com.inavi.maps.androiddemo.activity

import android.graphics.PointF
import android.view.View
import android.widget.CheckedTextView
import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.DpToPx
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_map_option_padding.*

class MapPaddingActivity : InvMapFragmentActivity(R.layout.activity_map_option_padding) {

  private val chkApplyPadding by lazy {
    chk_padding
  }

  private val paddingOverlay by lazy {
    cl_content_overlay
  }

  private fun setCheckListener(chk: CheckedTextView, changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(chk.isChecked)

    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      changedState?.invoke(chk.isChecked)
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val marker = InvMarker().apply {
      position = inaviMap.cameraPosition.target
      iconImage = InvMarkerIcons.RED
      anchor = PointF(0.5f,0.5f)
      map = inaviMap
    }

    setCheckListener(chkApplyPadding) { isChecked ->
      if (isChecked) {
        inaviMap.setPadding(25.DpToPx(), 25.DpToPx(), 100.DpToPx(), 100.DpToPx())
        paddingOverlay.visibility = View.VISIBLE
      } else {
        inaviMap.setPadding(0, 0, 0, 0)
        paddingOverlay.visibility = View.GONE
      }
      inaviMap.moveCamera(CameraUpdate.targetTo(marker.position))
    }
  }
}