package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.widget.CheckedTextView
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.InvMarker
import kotlinx.android.synthetic.main.activity_click_shape.*

class ShapeClickEventActivity : InvMapFragmentActivity(R.layout.activity_click_shape) {

  private val chkBtnHandleClick: CheckedTextView by lazy {
    chk_click_shape_click
  }
  private val chkBtnConsumeEvent: CheckedTextView by lazy {
    chk_click_shape_consume_click
  }

  private fun setCheckListener(chk: CheckedTextView, changedState: ((isChecked: Boolean) -> Unit)? = null) {
    changedState?.invoke(chk.isChecked)
    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      changedState?.invoke(chk.isChecked)
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {
    val invMarker = InvMarker().apply {
      tag = 0
      position = inaviMap.cameraPosition.target
      titleColor = Color.rgb(255, 0, 0)
      titleSize = 14f
      map = inaviMap
    }

    inaviMap.setOnMapClickListener { pointF, latLng ->
      Toast.makeText(this, getString(R.string.inv_format_event_map_click,
        "클릭", pointF.x, pointF.y, latLng.latitude, latLng.longitude), Toast.LENGTH_SHORT).show()
    }

    setCheckListener(chkBtnHandleClick) { isChecked ->
      if (isChecked) {
        invMarker.setOnClickListener {
          invMarker.tag = (invMarker.tag as Int) + 1
          invMarker.title = "마커 ${invMarker.tag}회 클릭"
          chkBtnConsumeEvent.isChecked
        }
      } else {
        invMarker.onClickListener = null
      }
      chkBtnConsumeEvent.isEnabled = isChecked
    }

    setCheckListener(chkBtnConsumeEvent)
  }
}
