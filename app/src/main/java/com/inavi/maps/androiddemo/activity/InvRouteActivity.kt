package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.graphics.PointF
import android.widget.CheckedTextView
import android.widget.SeekBar
import android.widget.TextView
import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.DpToPx
import com.inavi.maps.androiddemo.util.setCheckListener
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.*
import com.inavi.mapsdk.style.shapes.InvImage
import com.inavi.mapsdk.style.shapes.InvMarker
import com.inavi.mapsdk.style.shapes.InvRoute
import kotlinx.android.synthetic.main.activity_route.*

class InvRouteActivity : InvMapFragmentActivity(R.layout.activity_route, InvMapOptions().camera(INIT_CAMERA)) {
  private val checkedMarkerVisible: CheckedTextView by lazy {
    option_visible
  }

  private val checkedMarkerFlat: CheckedTextView by lazy {
    option_flat
  }

  private val seekBarPassRatio: SeekBar by lazy {
    seekbar_passRatio
  }

  private val tvPassRatio: TextView by lazy {
    tv_passRatio
  }

  var inaviMap: InaviMap? = null
  val passMarker: InvMarker by lazy { InvMarker() }
  val routeShape: InvRoute by lazy { InvRoute() }

  val normalIcon = InvImage(R.drawable.inv_marker_route_normal)
  val flatIcon   = InvImage(R.drawable.inv_marker_route_flat)

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap

    passMarker.apply {
      position = inaviMap.cameraPosition.target
    }.map = inaviMap

    routeShape.apply {
      lineWidth       = 9.DpToPx()
      strokeWidth     = 3.DpToPx()
      links           = LINKS
      passLineColor   = Color.LTGRAY
      passStrokeColor = Color.WHITE
    }.map = inaviMap

    routeShape.setOnRouteChangeListener { _, _, _ ->
      updatePassMarker()
    }

    checkedMarkerVisible.setCheckListener { isChecked ->
      checkedMarkerFlat.isEnabled = isChecked
      passMarker.isVisible = isChecked
      updatePassMarker()
    }

    checkedMarkerFlat.setCheckListener { isChecked ->
      passMarker.isIconFlat = isChecked

      if (passMarker.isIconFlat) {
        passMarker.iconImage  = flatIcon
        passMarker.isIconFlat = true
        passMarker.anchor     = PointF(0.5F, 0.5F)
      } else {
        passMarker.iconImage  = normalIcon
        passMarker.isIconFlat = false
        passMarker.anchor     = PointF(0.5F, 1.0F)
      }

      updatePassMarker()
    }

    seekBarPassRatio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        routeShape.passRatio = progress * 0.001
        tvPassRatio.text = "${(progress * 0.1).toInt()}%"

        inaviMap.moveCamera(CameraUpdate.from(CameraUpdateBuilder()
                .targetTo(routeShape.passPosition)
                .zoomTo(16.0)
                .tiltTo(60.0)
                .bearingTo(routeShape.passAngle)).apply {
          animationType = CameraAnimationType.Linear
          durationMs = 500
        })
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {
      }

      override fun onStopTrackingTouch(seekBar: SeekBar?) {
      }
    })
  }

  private fun updatePassMarker() {
    passMarker.position = routeShape.passPosition
    passMarker.angle = if (passMarker.isIconFlat) routeShape.passAngle.toFloat() else 0F
  }

  companion object {

    private val LINKS: List<InvRoute.InvRouteLink> = listOf(
      InvRoute.InvRouteLink(
        listOf(
          LatLng(37.39475, 127.11271),
          LatLng(37.39606, 127.11274),
          LatLng(37.39608, 127.11272),
          LatLng(37.39611, 127.11270),
          LatLng(37.39614, 127.11262),
          LatLng(37.39613, 127.11121),
          LatLng(37.39633, 127.11121)
        ),
        Color.RED, Color.WHITE),
      InvRoute.InvRouteLink(
        listOf(
          LatLng(37.39633, 127.11121),
          LatLng(37.39976, 127.11123)
        ),
        Color.GREEN, Color.WHITE),
      InvRoute.InvRouteLink(
        listOf(
          LatLng(37.39976, 127.11123),
          LatLng(37.40091, 127.11120)
        ),
        Color.BLUE, Color.WHITE),
      InvRoute.InvRouteLink(
        listOf(
          LatLng(37.40091, 127.11120),
          LatLng(37.40163, 127.11116),
          LatLng(37.40161, 127.11077),
          LatLng(37.40217, 127.11077)
        ),
        Color.RED, Color.WHITE)
    )

    private val INIT_CAMERA = CameraPosition(LatLng(37.39797, 127.11120), 14.0, 0.0, 0.0)
  }
}