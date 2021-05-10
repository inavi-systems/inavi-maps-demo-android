package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InaviMapSdk
import com.inavi.mapsdk.style.shapes.InvImage
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import com.inavi.mapsdk.style.shapes.InvPolyline
import kotlinx.android.synthetic.main.activity_map_custom_style.*

class MapCustomStyleActivity : InvMapFragmentActivity(R.layout.activity_map_custom_style) {
  var inaviMap: InaviMap? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initSpinner()
    updateSpinner()

    InaviMapSdk.getInstance(this).authSuccessCallback = InaviMapSdk.AuthSuccessCallback {
      if (it.isEmpty()) {
        Toast.makeText(this, R.string.inv_text_no_custom_style, Toast.LENGTH_SHORT).show()
      }
      updateSpinner()
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap

    val lineWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width)
    val patternInterval = resources.getDimensionPixelSize(R.dimen.shape_pattern_interval)

    InvPolyline().apply {
      coords = listOf(
        LatLng(37.40915, 127.11400),
        LatLng(37.40465, 127.10986),
        LatLng(37.40071, 127.11590),
        LatLng(37.39945, 127.10839)
      )
      color = Color.RED
      width = lineWidth
      map = inaviMap
    }

    InvPolyline().apply {
      coords = listOf(
        LatLng(37.39945, 127.10839),
        LatLng(37.39492, 127.11127)
      )
      color = Color.BLUE
      width = lineWidth
      setPattern(patternInterval, patternInterval)
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40219, 127.11077)
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40465, 127.10986)
      iconImage = InvImage(R.drawable.inv_marker_right_bottom)
      anchor = PointF(0.9f, 0.9f)
      angle = 90f
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40274, 127.10806)
      iconImage = InvMarkerIcons.BLUE
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.39990, 127.10965)
      iconImage = InvMarkerIcons.YELLOW
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40324, 127.11276)
      iconImage = InvMarkerIcons.GREEN
      alpha = 0.5f
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

  private fun initSpinner() {
    spin_map_custom_style.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {}
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        inaviMap?.customMapStyle =
          InaviMapSdk.getInstance(this@MapCustomStyleActivity).savedCustomMapStyles.getOrNull(
            position - 1
          )
      }
    }
  }

  private fun updateSpinner() {
    val styles = mutableListOf(getString(R.string.inv_text_default_style))

    val savedCustomMapStyles = InaviMapSdk.getInstance(this).savedCustomMapStyles
    styles.addAll(savedCustomMapStyles.map { it.styleName })
    spin_map_custom_style.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, styles)
  }
}