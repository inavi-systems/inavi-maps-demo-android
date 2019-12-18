package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.geometry.LatLngBounds
import com.inavi.mapsdk.maps.CameraAnimationType
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_fab.*

class CameraFitBoundsActivity : InvMapFragmentActivity(R.layout.activity_fab) {

  companion object {
    private val POSITION1 = LatLng(37.40219, 127.11077)
    private val POSITION2 = LatLng(36.99473, 127.81832)
  }

  private var isInitPosition = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    fab.setImageResource(R.drawable.ic_control_camera_24dp)
  }

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

    fab.setOnClickListener {
      val bounds = LatLngBounds(POSITION1, POSITION2)
      val padding = resources.getDimensionPixelSize(R.dimen.camera_bounds_padding)
      inaviMap.moveCamera(
        when (isInitPosition) {
          true -> CameraUpdate.fitBounds(bounds, padding)
          else -> CameraUpdate.newCameraPosition(InvConstants.POSITION_INAVI)
        }.setAnimationType(CameraAnimationType.Fly, 3000))
      fab.setImageResource(
        when (isInitPosition) {
          true -> R.drawable.ic_replay_24dp
          else -> R.drawable.ic_control_camera_24dp
        }
      )

      isInitPosition = !isInitPosition
    }

    inaviMap.addOnCameraChangeListener { reason ->
      if (isInitPosition && reason == CameraUpdate.UPDATE_REASON_GESTURE) {
        isInitPosition = false
        fab.setImageResource(R.drawable.ic_replay_24dp)
      }
    }
  }
}