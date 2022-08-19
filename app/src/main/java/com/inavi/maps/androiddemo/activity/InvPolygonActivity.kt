package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.widget.Checkable
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvPolygon
import kotlinx.android.synthetic.main.activity_inv_shape_remove.*

class InvPolygonActivity : InvMapFragmentActivity(R.layout.activity_inv_shape_remove, InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)

    private val POLYGON1_COORDS = listOf(
      LatLng(37.41014, 127.11011),
      LatLng(37.40915, 127.11400),
      LatLng(37.40538, 127.11440),
      LatLng(37.40465, 127.10986),
      LatLng(37.40755, 127.10610)
    )

    private val POLYGON2_COORDS = listOf(
      LatLng(37.39945, 127.10839),
      LatLng(37.40071, 127.11590),
      LatLng(37.39395, 127.11575),
      LatLng(37.39554, 127.10827)
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    rm_shape.text = getString(R.string.inv_remove_polygon)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val polygon1 = InvPolygon().apply {
      coords = POLYGON1_COORDS
      fillColor = ColorUtils.setAlphaComponent(Color.BLUE, 127)
      strokeColor = Color.BLUE
      strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
      map = inaviMap
    }

    val polygon2 = InvPolygon().apply {
      coords = POLYGON2_COORDS
      fillColor = ColorUtils.setAlphaComponent(Color.YELLOW, 127)
      strokeColor = Color.BLACK
      strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
      map = inaviMap
    }

    val allPolygons = listOf(polygon1, polygon2)

    rm_shape.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked

        allPolygons.forEach { polygon ->
          polygon.map = when (checked) {
            true -> inaviMap
            else -> null
          }
        }
      }
    }
  }
}