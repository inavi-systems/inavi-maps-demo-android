package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.support.v4.graphics.ColorUtils
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvCircle

class InvCircleActivity : InvMapFragmentActivity(InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    InvCircle().apply {
      center = INIT_CAMERA.target
      radius = 500.0
      fillColor = ColorUtils.setAlphaComponent(Color.RED, 127)
      strokeColor = Color.RED
      strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
      map = inaviMap
    }
  }
}