package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_map_custom_style.*

class MapTypeActivity : InvMapFragmentActivity(R.layout.activity_map_custom_style), OnMapReadyCallback {

    private val mapTypeNormal: String by lazy {
        getString(R.string.inv_text_normal_map)
    }
    private val mapTypeSatellite: String by lazy {
        getString(R.string.inv_text_satellite_map)
    }
    private val mapTypeHybrid: String by lazy {
        getString(R.string.inv_text_hybrid_map)
    }

    private var inaviMap: InaviMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spin_map_custom_style.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                changeMapType(position)
            }
        }

        tv_map_style.text = getString(R.string.inv_text_map_type)
        val types = listOf(mapTypeNormal, mapTypeSatellite, mapTypeHybrid)
        spin_map_custom_style.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, types)
    }

    override fun onMapReady(inaviMap: InaviMap) {
        this.inaviMap = inaviMap
        changeMapType(spin_map_custom_style.selectedItemPosition)
    }

    private fun changeMapType(position: Int) {
        when (spin_map_custom_style.getItemAtPosition(position)) {
            mapTypeNormal -> inaviMap?.mapType = InaviMap.MapType.Normal
            mapTypeSatellite -> inaviMap?.mapType = InaviMap.MapType.Satellite
            mapTypeHybrid -> inaviMap?.mapType = InaviMap.MapType.Hybrid
        }
    }
}