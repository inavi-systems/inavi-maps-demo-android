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
import com.inavi.mapsdk.style.shapes.InvCircle
import kotlinx.android.synthetic.main.activity_inv_shape_remove.*

class InvCircleActivity : InvMapFragmentActivity(R.layout.activity_inv_shape_remove, InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    rm_shape.text = getString(R.string.inv_remove_circle)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val circle1 = InvCircle().apply {
      center = INIT_CAMERA.target
      radius = 300.0
      fillColor = ColorUtils.setAlphaComponent(Color.RED, 127)
      strokeColor = Color.RED
      strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
      map = inaviMap
    }

    val circle2 = InvCircle().apply {
      center = LatLng(37.39478, 127.11116)
      radius = 200.0
      fillColor = ColorUtils.setAlphaComponent(Color.GREEN, 127)
      strokeColor = Color.GREEN
      strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
      map = inaviMap
    }

    val allCircles = listOf(circle1, circle2)

    rm_shape.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked

        allCircles.forEach { circle ->
          circle.map = when (checked) {
            true -> inaviMap
            else -> null
          }
        }
      }
    }
  }
}