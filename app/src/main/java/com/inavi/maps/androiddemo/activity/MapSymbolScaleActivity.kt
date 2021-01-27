package com.inavi.maps.androiddemo.activity

import com.inavi.maps.androiddemo.R
import com.inavi.maps.androiddemo.util.setCheckListener
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_map_option_symbol_scale.*


class MapSymbolScaleActivity : InvMapFragmentActivity(R.layout.activity_map_option_symbol_scale) {
  private val chkApplySymbolScale by lazy {
    chk_symbol_scale
  }

  override fun onMapReady(inaviMap: InaviMap) {
    chkApplySymbolScale.setCheckListener { isChecked ->
      inaviMap.symbolScale = if (isChecked) 1.6 else 1.0
    }
  }
}