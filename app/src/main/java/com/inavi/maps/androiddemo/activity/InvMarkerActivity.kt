package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.graphics.PointF
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvImage
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons

class InvMarkerActivity : InvMapFragmentActivity() {

  override fun onMapReady(inaviMap: InaviMap) {
    InvMarker().apply {
      position = LatLng(37.40219, 127.11077)
      title = "타이틀"
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40465, 127.10986)
      iconImage = InvImage(R.drawable.inv_marker_right_bottom)
      anchor = PointF(0.9f,0.9f)
      angle = 90f
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40274, 127.10806)
      iconImage = InvMarkerIcons.BLUE
      title = "타이틀 색상 적용"
      titleColor = Color.GREEN
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.39990, 127.10965)
      iconImage = InvMarkerIcons.YELLOW
      titleSize = 16f
      title = "타이틀 크기 적용"
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40324, 127.11276)
      iconImage = InvMarkerIcons.GREEN
      alpha = 0.5f
      title = "반투명 마커"
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40058, 127.11231)
      iconImage = InvImage(R.drawable.ic_star_black_24dp)
      iconScale = 2.0f
      anchor = PointF(0.5f, 0.5f)
      map = inaviMap
    }
  }
}