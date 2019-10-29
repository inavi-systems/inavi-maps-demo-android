package com.inavi.maps.androiddemo.activity

import android.widget.Checkable
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_inv_marker_overlap.*
import java.util.*
import kotlin.random.Random

class InvMarkerOverlapActivity : InvMapFragmentActivity(R.layout.activity_inv_marker_overlap) {

  override fun onMapReady(inaviMap: InaviMap) {
    val markers = ArrayList<InvMarker>()
    val bounds = inaviMap.visibleBounds
    val markerIcons = arrayListOf(InvMarkerIcons.RED, InvMarkerIcons.GREEN, InvMarkerIcons.BLUE, InvMarkerIcons.YELLOW, InvMarkerIcons.GRAY)

    for (i in 1..50) {
      InvMarker().apply {
        position = LatLng(
          (bounds.northLatitude - bounds.southLatitude) * Math.random() + bounds.southLatitude,
          (bounds.eastLongitude - bounds.westLongitude) * Math.random() + bounds.westLongitude
        )
        iconImage = markerIcons[Random.nextInt(markerIcons.size)]
        title = "마커 #$i"
        map = inaviMap
      }.let { markers.add(it) }
    }

    allow_overlap_markers.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked
        for (marker in markers) {
          marker.isAllowOverlapMarkers = !checked
        }
      }
    }

    allow_overlap_title.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked
        for (marker in markers) {
          marker.isAllowOverlapTitle = !checked
        }
      }
    }
  }
}