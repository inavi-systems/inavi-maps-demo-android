package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraAnimationType
import com.inavi.mapsdk.maps.CameraUpdate
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import kotlinx.android.synthetic.main.activity_camera_move.*

class CameraMoveActivity : InvMapFragmentActivity(R.layout.activity_camera_move) {

  companion object {
    private val POSITION1 = LatLng(37.40219, 127.11077)
    private val POSITION2 = LatLng(36.99473, 127.81832)
  }

  private var isInitPosition = true
  private var animationType = CameraAnimationType.None

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val options = resources.getStringArray(R.array.inv_option_array_animation_type)
    camera_animation_type.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
    camera_animation_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {}

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        animationType = when (position) {
          1 -> CameraAnimationType.Linear
          2 -> CameraAnimationType.Easing
          3 -> CameraAnimationType.Fly
          else -> CameraAnimationType.None
        }
      }
    }
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

    start_camera_move.setOnClickListener {
      inaviMap.moveCamera(CameraUpdate.targetTo(
        when (isInitPosition) {
          true -> POSITION2
          else -> POSITION1
        }
      ).setAnimationType(animationType, 3000))
      isInitPosition = !isInitPosition
    }
  }
}