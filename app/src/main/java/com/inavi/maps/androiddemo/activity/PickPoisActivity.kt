package com.inavi.maps.androiddemo.activity

import android.graphics.PointF
import android.graphics.RectF
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.DpToPx
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InaviMap.OnMapClickListener
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_pick_pois_map.*

class PickPoisActivity : InvMapFragmentActivity(R.layout.activity_pick_pois_map), OnMapClickListener {
  private val pickRectView: View by lazy {
    view_pick_rect
  }

  private val root: ConstraintLayout by lazy {
    cl_activity_click_map
  }

  private lateinit var inaviMap: InaviMap

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap
    inaviMap.addOnCameraChangeListener {
      pickRectView.visibility = View.GONE
      inaviMap.clearShapes()
    }
    inaviMap.onMapClickListener = this
  }

  override fun onMapClick(pointF: PointF, latLng: LatLng) {
    val touchSize = 20.DpToPx()
    val touchRect = RectF(
      pointF.x - touchSize / 2,
      pointF.y - touchSize / 2,
      pointF.x + touchSize / 2,
      pointF.y + touchSize / 2,
    )
    drawPickRect(touchSize, pointF)

    inaviMap.clearShapes()
    val pois = inaviMap.pickPois(touchRect)
    when (pois.isNotEmpty()) {
      true -> {
        val message = pois.joinToString("\n") { poi -> String.format(getString(R.string.inv_text_poi_info, poi.name, poi.poiId)) }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        pois.forEach { poi ->
          InvMarker(poi.position, InvMarkerIcons.RED).apply {
            isAllowOverlapMarkers = true
            isAllowOverlapSymbols = true
            iconScale = 0.5f
            map = inaviMap
          }
        }
      }
      else -> Toast.makeText(this, getString(R.string.inv_text_no_poi), Toast.LENGTH_SHORT).show()
    }
  }

  private fun drawPickRect(touchSize: Int, pointF: PointF) {
    ConstraintSet().apply {
      clone(root)
      constrainWidth(pickRectView.id, touchSize)
      constrainHeight(pickRectView.id, touchSize)
      connect(pickRectView.id, ConstraintSet.START, root.id, ConstraintSet.START, pointF.x.toInt() - touchSize / 2)
      connect(pickRectView.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP, pointF.y.toInt() - touchSize / 2)
    }.also { it.applyTo(root) }
    pickRectView.visibility = View.VISIBLE
  }
}