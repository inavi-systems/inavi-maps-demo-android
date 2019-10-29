package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.maps.InvMapFragment
import com.inavi.mapsdk.maps.InvMapOptions

open class InvMapFragmentActivity(private val layoutId: Int, private val options: InvMapOptions?) : InvBaseActivity() {

  constructor() : this(R.layout.activity_inv_map_fragment, null)
  constructor(layoutId: Int) : this(layoutId, null)
  constructor(options: InvMapOptions?) : this(R.layout.activity_inv_map_fragment, options)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutId)

    var mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as InvMapFragment?
    if (mapFragment == null) {
      mapFragment = InvMapFragment.newInstance(options).also {
        supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
      }
    }

    mapFragment.getMapAsync(this)
  }

  override fun onMapReady(inaviMap: InaviMap) {
  }
}