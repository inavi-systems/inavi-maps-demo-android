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

  private var inaviMap: InaviMap? = null
  private var isInitPosition = true
  private var isMovingByButton = false

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap

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
      showCameraPositionInfo(true)
    }
    inaviMap.addOnCameraIdleListener {
      showCameraPositionInfo(false)
    }

    text_camera_info.visibility = View.VISIBLE
    showCameraPositionInfo(false)

    fab.setOnClickListener {
      if (isMovingByButton) {
        inaviMap.cancelTransitions()
        return@setOnClickListener
      }

      inaviMap.moveCamera(CameraUpdate.newCameraPosition(
        when (isInitPosition) {
          true -> CAMERA_POSITION2
          else -> CAMERA_POSITION1
        }).setAnimationType(CameraAnimationType.Fly, 3000)
        .setCancelCallback {
          isMovingByButton = false
          fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
          Toast.makeText(this@CameraEventsActivity, R.string.inv_toast_camera_update_cancelled, Toast.LENGTH_SHORT).show()
        }
        .setFinishCallback {
          isMovingByButton = false
          fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
          Toast.makeText(this@CameraEventsActivity, R.string.inv_toast_camera_update_finished, Toast.LENGTH_SHORT).show()
        })

      isMovingByButton = true
      fab.setImageResource(R.drawable.ic_stop_black_24dp)

      isInitPosition = !isInitPosition
    }
  }

  private fun showCameraPositionInfo(isMoving: Boolean) {
    val statusText = if (isMoving) "이동" else "대기"
    inaviMap?.cameraPosition?.let {
      text_camera_info.text = getString(R.string.inv_format_camera_info,
        statusText, it.target.latitude, it.target.longitude, it.zoom, it.tilt, it.bearing)
    }
  }
}