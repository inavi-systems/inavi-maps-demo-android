package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.CameraPosition
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapOptions
import com.inavi.mapsdk.style.shapes.InvCircle
import com.inavi.mapsdk.style.shapes.InvMultiLine
import java.util.*

class InvMultiLineActivity : InvMapFragmentActivity(InvMapOptions().camera(INIT_CAMERA)) {

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 14.0, 0.0, 0.0)

    private val COORDS = listOf(
      listOf(
        LatLng(37.39481, 127.11266),
        LatLng(37.39491, 127.11284),
        LatLng(37.39513, 127.11295),
        LatLng(37.39539, 127.11295),
        LatLng(37.39566, 127.11295),
        LatLng(37.39598, 127.11294),
        LatLng(37.39615, 127.11294),
        LatLng(37.39637, 127.11294),
        LatLng(37.39660, 127.11294),
        LatLng(37.39688, 127.11295),
        LatLng(37.39737, 127.11294),
        LatLng(37.39758, 127.11294),
        LatLng(37.39802, 127.11295),
        LatLng(37.39838, 127.11294)),
      listOf(
        LatLng(37.39838, 127.11294),
        LatLng(37.39851, 127.11295),
        LatLng(37.39876, 127.11294),
        LatLng(37.39901, 127.11294),
        LatLng(37.39925, 127.11294),
        LatLng(37.39966, 127.11295),
        LatLng(37.39977, 127.11295),
        LatLng(37.39996, 127.11296),
        LatLng(37.40025, 127.11296),
        LatLng(37.40069, 127.11295),
        LatLng(37.40108, 127.11294),
        LatLng(37.40140, 127.11294),
        LatLng(37.40173, 127.11294),
        LatLng(37.40198, 127.11295),
        LatLng(37.40281, 127.11296)),
      listOf(
        LatLng(37.40281, 127.11296),
        LatLng(37.40301, 127.11294),
        LatLng(37.40322, 127.11292),
        LatLng(37.40344, 127.11289),
        LatLng(37.40364, 127.11285),
        LatLng(37.40386, 127.11280),
        LatLng(37.40408, 127.11272),
        LatLng(37.40420, 127.11268),
        LatLng(37.40437, 127.11262),
        LatLng(37.40459, 127.11254),
        LatLng(37.40486, 127.11241),
        LatLng(37.40501, 127.11233),
        LatLng(37.40519, 127.11222),
        LatLng(37.40532, 127.11212),
        LatLng(37.40549, 127.11201),
        LatLng(37.40563, 127.11192),
        LatLng(37.40573, 127.11184)),
      listOf(
        LatLng(37.40573, 127.11184),
        LatLng(37.40595, 127.11168),
        LatLng(37.40617, 127.11149),
        LatLng(37.40640, 127.11125),
        LatLng(37.40656, 127.11109),
        LatLng(37.40677, 127.11089),
        LatLng(37.40694, 127.11070),
        LatLng(37.40709, 127.11052),
        LatLng(37.40726, 127.11033),
        LatLng(37.40749, 127.11008),
        LatLng(37.40763, 127.10990),
        LatLng(37.40772, 127.10978)),
      listOf(
        LatLng(37.40772, 127.10978),
        LatLng(37.40788, 127.10959),
        LatLng(37.40807, 127.10935),
        LatLng(37.40824, 127.10914),
        LatLng(37.40840, 127.10895),
        LatLng(37.40858, 127.10874),
        LatLng(37.40876, 127.10852),
        LatLng(37.40890, 127.10834),
        LatLng(37.40913, 127.10807),
        LatLng(37.40925, 127.10792),
        LatLng(37.40944, 127.10768),
        LatLng(37.40966, 127.10739),
        LatLng(37.40990, 127.10710),
        LatLng(37.41018, 127.10672))
    )

    private val COLORS = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.RED, Color.GREEN)
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val lineWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width)
    val patternInterval = resources.getDimensionPixelSize(R.dimen.shape_pattern_interval)
    val invLines = ArrayList<InvMultiLine.InvLine>()
    for (i in COORDS.indices) {
      invLines.add(InvMultiLine.InvLine(COORDS[i], COLORS[i]))
    }

    InvMultiLine().apply {
      width = lineWidth
      setPattern(patternInterval, patternInterval)
      lines = invLines
      map = inaviMap
    }
  }
}