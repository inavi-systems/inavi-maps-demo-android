package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_inv_map_view.*

class InvMapViewActivity : InvBaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_inv_map_view)

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync(this)
  }

  override fun onStart() {
    super.onStart()
    mapView.onStart()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onStop() {
    super.onStop()
    mapView.onStop()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }

  override fun onMapReady(inaviMap: InaviMap) {
  }
}