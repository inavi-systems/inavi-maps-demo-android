package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Checkable
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvPolyline
import kotlinx.android.synthetic.main.activity_inv_shape_remove.*

class InvPolylineActivity : InvMapFragmentActivity(R.layout.activity_inv_shape_remove, InvMapOptions().camera(INIT_CAMERA)) {

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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    rm_shape.text = getString(R.string.inv_remove_polyline)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val lineWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width)
    val patternInterval = resources.getDimensionPixelSize(R.dimen.shape_pattern_interval)

    val polyline1 = InvPolyline().apply {
      coords = POLYLINE1_COORDS
      color = Color.RED
      width = lineWidth
      map = inaviMap
    }

    val polyline2 = InvPolyline().apply {
      coords = POLYLINE2_COORDS
      color = Color.BLUE
      width = lineWidth
      setPattern(patternInterval, patternInterval)
      map = inaviMap
    }

    val allPolylines = listOf(polyline1, polyline2)

    rm_shape.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked

        allPolylines.forEach { polyline ->
          polyline.map = when (checked) {
            true -> inaviMap
            else -> null
          }
        }
      }
    }
  }
}