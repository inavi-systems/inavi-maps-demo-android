package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapFragment
import kotlinx.android.synthetic.main.activity_map_custom_style.*

class MapTypeActivity : InvMapFragmentActivity(R.layout.activity_map_custom_style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSpinner()
        settingSpinner()
    }

    private fun initSpinner() {

        var mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as InvMapFragment?

        spin_map_custom_style.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (mapFragment == null) {
                    mapFragment = InvMapFragment.newInstance().also {
                        supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
                    }
                }

                mapFragment?.getMapAsync { inaviMap ->
                    when (spin_map_custom_style.getItemAtPosition(position)) {
                        "일반 지도" -> {
                            inaviMap.mapType = InaviMap.MapType.Normal
                        }
                        "항공 지도" -> {
                            inaviMap.mapType = InaviMap.MapType.Satellite
                        }
                        "하이브리드 지도" -> {
                            inaviMap.mapType = InaviMap.MapType.Hybrid
                        }
                    }
                }
            }
        }
    }

    private fun settingSpinner() {
        tv_map_style.text = getString(R.string.inv_text_map_type)
        val type = mutableListOf(getString(R.string.inv_text_normal_map), getString(R.string.inv_text_satellite_map), getString(R.string.inv_text_hybrid_map))
        spin_map_custom_style.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, type)
    }
}