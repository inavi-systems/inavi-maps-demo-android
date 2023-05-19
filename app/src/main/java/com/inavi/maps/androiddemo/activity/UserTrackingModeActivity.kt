package com.inavi.maps.androiddemo.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.inavi.maps.androiddemo.BuildConfig
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.*
import kotlinx.android.synthetic.main.activity_user_tracking_mode.*

class UserTrackingModeActivity : InvMapFragmentActivity(R.layout.activity_user_tracking_mode, InvMapOptions().locationButtonVisible(true)),
  OnUserTrackingModeChangedListener, AdapterView.OnItemSelectedListener {

  companion object {
    private const val ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION"
  }

  private var userTrackingMode = UserTrackingMode.Tracking

  private var inaviMap: InaviMap? = null
  private var locationProvider: FusedLocationProvider? = null

  private val spinnerTracking: Spinner by lazy {
    spinner_location
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val options = resources.getStringArray(R.array.inv_option_array_location_tracking_mode)
    spinnerTracking.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
    spinnerTracking.onItemSelectedListener = this
    locationProvider = FusedLocationProvider(this@UserTrackingModeActivity) { isGranted ->
      Snackbar.make(spinnerTracking, "위치 권한을 ${if (isGranted) "허용" else "거부"} 하였습니다.", Snackbar.LENGTH_SHORT).show()
      if (!isGranted) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this@UserTrackingModeActivity, ACCESS_FINE_LOCATION)) {
          AlertDialog.Builder(this@UserTrackingModeActivity).apply {
            setMessage(R.string.inv_text_popup_location_permission_denied)
            setNegativeButton(R.string.inv_text_popup_settings) { _, _ ->
              Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)).let {
                startActivity(it)
              }
            }
            setPositiveButton(R.string.inv_text_popup_ok, null)
            setCancelable(false)
          }.show()
        }
        inaviMap?.userTrackingMode = UserTrackingMode.None
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    locationProvider = null
  }

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap
    inaviMap.addOnUserTrackingModeChangedListener(this)
    inaviMap.locationProvider = locationProvider
    inaviMap.userTrackingMode = userTrackingMode
  }

  override fun onTrackingModeChanged(userTrackingMode: UserTrackingMode) {
    this.userTrackingMode = userTrackingMode
    when (userTrackingMode) {
      UserTrackingMode.None -> spinnerTracking.setSelection(0)
      UserTrackingMode.NoTracking -> spinnerTracking.setSelection(1)
      UserTrackingMode.Tracking -> spinnerTracking.setSelection(2)
      UserTrackingMode.TrackingCompass -> spinnerTracking.setSelection(3)
    }
  }

  private fun setUserTrackingMode(mode: UserTrackingMode) {
    inaviMap?.userTrackingMode = mode
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
  }

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    when (position) {
      0 -> setUserTrackingMode(UserTrackingMode.None)
      1 -> setUserTrackingMode(UserTrackingMode.NoTracking)
      2 -> setUserTrackingMode(UserTrackingMode.Tracking)
      3 -> setUserTrackingMode(UserTrackingMode.TrackingCompass)
    }
  }
}