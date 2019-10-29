package com.inavi.maps.androiddemo.activity

import android.widget.CheckedTextView
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_click_map.*

class MapClickEventActivity : InvMapFragmentActivity(R.layout.activity_click_map) {

  private val chkBtnHandleClick: CheckedTextView by lazy {
    chk_click_map_click
  }

  private val chkBtnHandleLongClick: CheckedTextView by lazy {
    chk_click_map_long_click
  }

  private val chkBtnHandleDoubleClick: CheckedTextView by lazy {
    chk_click_map_double_click
  }

  private val chkBtnConsumeEvent: CheckedTextView by lazy {
    chk_click_map_consume_double_click
  }

  private fun addCheckListener(chk: CheckedTextView, changedState: ((Boolean) -> Unit)?) {
    if (changedState != null) changedState(chk.isChecked)

    chk.setOnClickListener {
      chk.isChecked = !chk.isChecked
      if (changedState != null) changedState(chk.isChecked)
    }
  }

  override fun onMapReady(inaviMap: InaviMap) {
    addCheckListener(chkBtnHandleClick) { isChecked ->
      if (isChecked) {
        inaviMap.setOnMapClickListener { pointF, latLng ->
          Toast.makeText(this, getString(R.string.inv_format_event_map_click,
            "클릭", pointF.x, pointF.y, latLng.latitude, latLng.longitude), Toast.LENGTH_SHORT).show()
        }
      } else {
        inaviMap.onMapClickListener = null
      }
    }

    addCheckListener(chkBtnHandleLongClick) { isChecked ->
      if (isChecked) {
        inaviMap.setOnMapLongClickListener { pointF, latLng ->
          Toast.makeText(this, getString(R.string.inv_format_event_map_click,
            "롱 클릭", pointF.x, pointF.y, latLng.latitude, latLng.longitude), Toast.LENGTH_SHORT).show()
        }
      } else {
        inaviMap.onMapLongClickListener = null
      }
    }

    addCheckListener(chkBtnHandleDoubleClick) { isChecked ->
      if (isChecked) {
        inaviMap.setOnMapDoubleClickListener { pointF, latLng ->
          Toast.makeText(this, getString(R.string.inv_format_event_map_click,
            "더블 클릭", pointF.x, pointF.y, latLng.latitude, latLng.longitude), Toast.LENGTH_SHORT).show()
          chkBtnConsumeEvent.isChecked
        }
      } else {
        inaviMap.onMapDoubleClickListener = null
      }
      chkBtnConsumeEvent.isEnabled = isChecked
    }

    addCheckListener(chkBtnConsumeEvent, null)
  }
}