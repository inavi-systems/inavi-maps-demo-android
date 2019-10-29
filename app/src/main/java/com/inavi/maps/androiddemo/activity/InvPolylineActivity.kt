package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvPolyline

class InvPolylineActivity : InvMapFragmentActivity(InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)

    private val POLYLINE1_COORDS = listOf(
      LatLng(37.40915, 127.11400),
      LatLng(37.40465, 127.10986),
      LatLng(37.40071, 127.11590),
      LatLng(37.39945, 127.10839)
    )

    private val POLYLINE2_COORDS = listOf(
      LatLng(37.39945, 127.10839),
      LatLng(37.39492, 127.11127)
    )
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val lineWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width)
    val patternInterval = resources.getDimensionPixelSize(R.dimen.shape_pattern_interval)

    InvPolyline().apply {
      coords = POLYLINE1_COORDS
      color = Color.RED
      width = lineWidth
      map = inaviMap
    }

    InvPolyline().apply {
      coords = POLYLINE2_COORDS
      color = Color.BLUE
      width = lineWidth
      setPattern(patternInterval, patternInterval)
      map = inaviMap
    }
  }
}