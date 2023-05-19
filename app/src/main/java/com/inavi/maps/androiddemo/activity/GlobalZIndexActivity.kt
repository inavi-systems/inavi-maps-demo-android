package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.widget.Checkable
import androidx.core.graphics.ColorUtils
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvCircle
import com.inavi.mapsdk.style.shapes.InvPolyline
import kotlinx.android.synthetic.main.activity_global_z_index.*

class GlobalZIndexActivity : InvMapFragmentActivity(R.layout.activity_global_z_index, InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)

    private val POLYLINE_COORDS = listOf(
      LatLng(37.40872, 127.11401),
      LatLng(37.40429, 127.10986),
      LatLng(37.40052, 127.11595),
      LatLng(37.39930, 127.10840),
      LatLng(37.39486, 127.11122)
    )
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

    val polyline = InvPolyline().apply {
      coords = POLYLINE_COORDS
      color = Color.BLUE
      width = resources.getDimensionPixelSize(R.dimen.shape_line_width)
      map = inaviMap
    }

    polyline_under_circle.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked
        polyline.globalZIndex = when (!checked) {
          true -> InvCircle.DEFAULT_GLOBAL_Z_INDEX - 1
          else -> InvCircle.DEFAULT_GLOBAL_Z_INDEX + 1
        }
      }
    }
  }
}