package com.inavi.maps.androiddemo.activity

import android.graphics.PointF
import android.view.View
import android.widget.TextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_coords_projection.*

class ProjectionActivity : InvMapFragmentActivity(R.layout.activity_coords_projection) {

  private val crosshairPoint = PointF()
  private var inaviMap: InaviMap? = null

  private val ivProjectionCrosshair: View by lazy {
    iv_projection_cross
  }

  private val tvProjectionInfoScreen: TextView by lazy {
    tv_projection_info_screen
  }

  private val tvProjectionInfoMap: TextView by lazy {
    tv_projection_info_map
  }

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap

    inaviMap.addOnCameraChangeListener {
      updateProjection()
    }
    updateProjection()
  }

  private fun updateProjection() {
    val map = inaviMap ?: return

    crosshairPoint.set(ivProjectionCrosshair.x + ivProjectionCrosshair.width / 2, ivProjectionCrosshair.y + ivProjectionCrosshair.height / 2)
    tvProjectionInfoScreen.text = getString(R.string.inv_format_misc_projection_coords_screen, crosshairPoint.x, crosshairPoint.y)

    val latLng = map.projection.getLatLngFromPoint(crosshairPoint)
    tvProjectionInfoMap.text = getString(R.string.inv_format_misc_projection_coords_map, latLng.latitude, latLng.longitude)
  }
}