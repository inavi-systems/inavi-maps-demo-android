package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.setCheckListener
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.geometry.LatLngBounds
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvPolyline
import kotlinx.android.synthetic.main.activity_map_option_restriction.*


class MapRestrictionActivity : InvMapFragmentActivity(R.layout.activity_map_option_restriction) {

  companion object {
    private val BOUNDS = LatLngBounds.Builder().includeAll(listOf(LatLng(37.413294, 127.269311), LatLng(37.715133, 126.734086))).build()
  }

  private val chkApplyRestrict by lazy {
    chk_restriction
  }

  override fun onMapReady(inaviMap: InaviMap) {
    inaviMap.moveCamera(CameraUpdate.fitBounds(BOUNDS))
    inaviMap.moveCamera(CameraUpdate.zoomBy(-0.5))

    val polyline = InvPolyline().apply {
      coords = listOf(BOUNDS.northWest, BOUNDS.northEast, BOUNDS.southEast, BOUNDS.southWest, BOUNDS.northWest)
      color = Color.RED
      map = inaviMap
    }

    chkApplyRestrict.setCheckListener { isChecked ->
      if (isChecked) {
        inaviMap.constraintBounds = BOUNDS
        val cameraPosition = inaviMap.getCameraFitBounds(BOUNDS)
        inaviMap.moveCamera(CameraUpdate.targetTo(cameraPosition.target))
        inaviMap.moveCamera(CameraUpdate.zoomTo(cameraPosition.zoom - 0.5))
        inaviMap.minZoom = cameraPosition.zoom - 0.5
        polyline.isVisible = true
      } else {
        inaviMap.constraintBounds = InvConstants.BOUNDS_KOREA
        inaviMap.minZoom = InvConstants.MINIMUM_ZOOM
        polyline.isVisible = false
      }
    }
  }
}