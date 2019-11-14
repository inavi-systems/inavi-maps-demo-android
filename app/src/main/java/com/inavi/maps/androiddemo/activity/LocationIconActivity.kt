package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.widget.*
import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.DpToPx
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.LocationIcon
import com.inavi.mapsdk.style.shapes.InvImage
import kotlinx.android.synthetic.main.activity_location_icon.*

class LocationIconActivity : InvMapFragmentActivity(R.layout.activity_location_icon) {

  private var inaviMap: InaviMap? = null
  private var locationIcon: LocationIcon? = null

  private val checkedOptionVisible: CheckedTextView by lazy {
    option_visible
  }

  private val checkedOptionImage: CheckedTextView by lazy {
    option_image
  }

  private val checkedOptionScale: CheckedTextView by lazy {
    option_scale
  }

  private val checkedOptionCircleRadius: CheckedTextView by lazy {
    option_circle_radius
  }

  private val checkedOptionCircleColor: CheckedTextView by lazy {
    option_circle_color
  }

  private val llBearing: LinearLayout by lazy {
    option_bearing
  }

  private val seekBarBearing: SeekBar by lazy {
    seekbar_bearing
  }

  private val tvBearingValue: TextView by lazy {
    tv_bearing_value
  }

  private fun setCheckListener(chk: CheckedTextView, changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(chk.isChecked)
    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      changedState?.invoke(chk.isChecked)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    seekBarBearing.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        locationIcon?.bearing = progress.toFloat()
        tvBearingValue.text = getString(R.string.inv_format_location_icon_bearing_value, progress)
      }

      override fun onStartTrackingTouch(seekBar: SeekBar) {
      }

      override fun onStopTrackingTouch(seekBar: SeekBar) {
      }
    })
  }

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap
    this.locationIcon = inaviMap.locationIcon

    locationIcon?.position = inaviMap.cameraPosition.target
    locationIcon?.setOnClickListener { shape ->
      Toast.makeText(this, getString(R.string.inv_toast_location_icon_click), Toast.LENGTH_SHORT).show()
      true
    }

    inaviMap.setOnMapClickListener { _, latLng ->
      locationIcon?.position = latLng
    }

    setCheckListener(checkedOptionVisible) { isChecked ->
      locationIcon?.isVisible = isChecked
      checkedOptionImage.isEnabled = isChecked
      checkedOptionScale.isEnabled = isChecked
      checkedOptionCircleRadius.isEnabled = isChecked
      checkedOptionCircleColor.isEnabled = isChecked
      for (i in 0 until llBearing.childCount) {
        llBearing.getChildAt(i)?.isEnabled = isChecked
      }
    }

    setCheckListener(checkedOptionImage) { isChecked ->
      locationIcon?.image = when (isChecked) {
        true -> InvImage(R.drawable.baseline_directions_run_black_36dp)
        else -> LocationIcon.DEFAULT_IMAGE
      }
    }

    setCheckListener(checkedOptionScale) { isChecked ->
      locationIcon?.scale = when (isChecked) {
        true -> 2.0f
        else -> 1.0f
      }
    }

    setCheckListener(checkedOptionCircleRadius) { isChecked ->
      locationIcon?.circleRadius = when (isChecked) {
        true -> 48.DpToPx()
        else -> LocationIcon.DEFAULT_CIRCLE_RADIUS.DpToPx()
      }
    }

    setCheckListener(checkedOptionCircleColor) { isChecked ->
      locationIcon?.circleColor = when (isChecked) {
        true -> ColorUtils.setAlphaComponent(Color.YELLOW, 128)
        else -> LocationIcon.DEFAULT_CIRCLE_COLOR
      }
    }
  }
}