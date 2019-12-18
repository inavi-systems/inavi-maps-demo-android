package com.inavi.maps.androiddemo.activity

import android.view.View
import android.widget.Checkable
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraAnimationType
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.CameraUpdateBuilder
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_camera_move.start_camera_move
import kotlinx.android.synthetic.main.activity_camera_update_builder.*

class CameraUpdateBuilderActivity : InvMapFragmentActivity(R.layout.activity_camera_update_builder) {

  companion object {
    private val POSITION1 = LatLng(37.40219, 127.11077)
    private val POSITION2 = LatLng(36.99473, 127.81832)
  }

  private var isInitPosition = true

  override fun onMapReady(inaviMap: InaviMap) {
    InvMarker().apply {
      position = POSITION1
      iconImage = InvMarkerIcons.RED
      map = inaviMap
    }

    InvMarker().apply {
      position = POSITION2
      iconImage = InvMarkerIcons.BLUE
      map = inaviMap
    }

    val optionClickListener = View.OnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked
      }
    }

    camera_update_target.setOnClickListener(optionClickListener)
    camera_update_zoom.setOnClickListener(optionClickListener)
    camera_update_tilt.setOnClickListener(optionClickListener)
    camera_update_bearing.setOnClickListener(optionClickListener)

    start_camera_move.setOnClickListener {
      var duration = 1000L

      val builder = CameraUpdateBuilder().apply {
        if (camera_update_target.isChecked) {
          targetTo(when (isInitPosition) {
            true -> POSITION2
            else -> POSITION1
          })
          isInitPosition = !isInitPosition
          duration = 5000L
        }
        val cameraPosition = inaviMap.cameraPosition
        if (camera_update_zoom.isChecked) {
          var zoomDelta = 3.0
          if (cameraPosition.zoom + zoomDelta >= InvConstants.MAXIMUM_ZOOM) zoomDelta *= -1
          zoomBy(zoomDelta)
        }
        if (camera_update_tilt.isChecked) {
          var tiltDelta = 10.0
          if (cameraPosition.tilt + tiltDelta >= InvConstants.MAXIMUM_TILT) tiltDelta *= -1
          tiltBy(tiltDelta)
        }
        if (camera_update_bearing.isChecked) bearingBy(30.0)
      }

      inaviMap.moveCamera(CameraUpdate.from(builder).setAnimationType(CameraAnimationType.Fly, duration))
    }
  }
}