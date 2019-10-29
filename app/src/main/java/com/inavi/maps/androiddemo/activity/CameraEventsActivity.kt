package com.inavi.maps.androiddemo.activity

import android.view.View
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraAnimationType
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_camera_events.*

class CameraEventsActivity : InvMapFragmentActivity(R.layout.activity_camera_events) {

  companion object {
    private val CAMERA_POSITION1 = CameraPosition(LatLng(37.40219, 127.11077), 14.0, 0.0, 0.0)
    private val CAMERA_POSITION2 = CameraPosition(LatLng(36.99473, 127.81832), 14.0, 0.0, 0.0)
  }

  private var isInitPosition = true
  private var isMoving = false

  override fun onMapReady(inaviMap: InaviMap) {
    InvMarker().apply {
      position = CAMERA_POSITION1.target
      iconImage = InvMarkerIcons.RED
      map = inaviMap
    }

    InvMarker().apply {
      position = CAMERA_POSITION2.target
      iconImage = InvMarkerIcons.BLUE
      map = inaviMap
    }

    inaviMap.addOnCameraChangeListener {
      val position = inaviMap.cameraPosition
      text_camera_info.text = getString(R.string.inv_format_camera_info,
        "이동", position.target.latitude, position.target.longitude, position.zoom, position.tilt, position.bearing)
    }
    inaviMap.addOnCameraIdleListener {
      val position = inaviMap.cameraPosition
      text_camera_info.text = getString(R.string.inv_format_camera_info,
        "대기", position.target.latitude, position.target.longitude, position.zoom, position.tilt, position.bearing)
    }
    text_camera_info.visibility = View.VISIBLE

    fab.setOnClickListener {
      if (isMoving) {
        inaviMap.cancelTransitions()
        return@setOnClickListener
      }

      inaviMap.moveCamera(CameraUpdate.newCameraPosition(
        when (isInitPosition) {
          true -> CAMERA_POSITION2
          else -> CAMERA_POSITION1
        }).setAnimationType(CameraAnimationType.Fly, 3000)
        .setCancelCallback {
          isMoving = false
          fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
          Toast.makeText(this@CameraEventsActivity, R.string.inv_toast_camera_update_cancelled, Toast.LENGTH_SHORT).show()
        }
        .setFinishCallback {
          isMoving = false
          fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
          Toast.makeText(this@CameraEventsActivity, R.string.inv_toast_camera_update_finished, Toast.LENGTH_SHORT).show()
        })

      isMoving = true
      fab.setImageResource(R.drawable.ic_stop_black_24dp)

      isInitPosition = !isInitPosition
    }
  }
}