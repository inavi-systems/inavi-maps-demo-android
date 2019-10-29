package com.inavi.maps.androiddemo.activity

import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.LocationProvider
import com.inavi.mapsdk.maps.UserTrackingMode
import kotlinx.android.synthetic.main.activity_location.*

class CustomLocationActivity : InvMapFragmentActivity(R.layout.activity_location), AdapterView.OnItemSelectedListener {

  companion object {
    private val LOCATION_SEOUL = Location("서울시청").apply {
      latitude = 37.56668
      longitude = 126.97815
    }

    private val LOCATION_BUSAN = Location("부산역").apply {
      latitude = 35.11523
      longitude = 129.04122
    }
  }

  private var inaviMap: InaviMap? = null
  private var locationChangedListener: LocationProvider.OnLocationChangedListener? = null

  private val spinnerCustom: Spinner by lazy {
    spinner_location
  }

  private val tvCustom: TextView by lazy {
    tv_location
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val options = resources.getStringArray(R.array.inv_option_array_location_custom)
    spinnerCustom.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
    spinnerCustom.onItemSelectedListener = this
    tvCustom.text = getString(R.string.inv_text_location_custom)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap
    inaviMap.locationProvider = CustomLocationProvider()
    inaviMap.userTrackingMode = UserTrackingMode.NoTracking
    inaviMap.addOnCameraChangeListener { reason ->
      if (reason == CameraUpdate.UPDATE_REASON_CONTROL || reason == CameraUpdate.UPDATE_REASON_GESTURE) {
        spinnerCustom.setSelection(0)
        changeLocation(null)
      }
    }
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
  }

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    when (position) {
      0 -> changeLocation(null)
      1 -> changeLocation(LOCATION_SEOUL)
      2 -> changeLocation(LOCATION_BUSAN)
    }
  }

  private fun changeLocation(location: Location?) {
    if (location == null) {
      if (inaviMap?.userTrackingMode != UserTrackingMode.NoTracking) {
        inaviMap?.userTrackingMode = UserTrackingMode.NoTracking
      }
    } else {
      if (inaviMap?.userTrackingMode != UserTrackingMode.Tracking) {
        inaviMap?.userTrackingMode = UserTrackingMode.Tracking
      }
      locationChangedListener?.onLocationChanged(location)
    }
  }

  private inner class CustomLocationProvider : LocationProvider {
    override fun activate(listener: LocationProvider.OnLocationChangedListener) {
      locationChangedListener = listener
    }

    override fun deactivate() {
      locationChangedListener = null
    }
  }
}