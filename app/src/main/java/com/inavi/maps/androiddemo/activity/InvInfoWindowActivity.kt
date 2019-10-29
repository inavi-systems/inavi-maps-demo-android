package com.inavi.maps.androiddemo.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Checkable
import android.widget.ImageView
import android.widget.TextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.InaviMap
import com.inavi.mapsdk.style.shapes.*
import kotlinx.android.synthetic.main.activity_inv_info_window.*

class InvInfoWindowActivity : InvMapFragmentActivity(R.layout.activity_inv_info_window) {

  override fun onMapReady(inaviMap: InaviMap) {
    val defaultAdapter = InfoWindowAdapter(this)
    val customAdapter = CustomInfoWindowViewAdapter(this)

    val infoWindow = InvInfoWindow().apply {
      position = LatLng(37.40219, 127.11077)
      adapter = defaultAdapter
      setOnClickListener {
        map = null
        true
      }
    }
    infoWindow.map = inaviMap

    InvMarker().apply {
      position = LatLng(37.40465, 127.10986)
      iconImage = InvMarkerIcons.RED
      setOnClickListener {
        infoWindow.setMarker(this)
        if (!infoWindow.isAttached) {
          infoWindow.map = inaviMap
        }
        true
      }
      tag = "RED"
      map = inaviMap
    }

    InvMarker().apply {
      position = LatLng(37.40058, 127.11231)
      iconImage = InvMarkerIcons.BLUE
      setOnClickListener {
        infoWindow.setMarker(this)
        if (!infoWindow.isAttached) {
          infoWindow.map = inaviMap
        }
        true
      }
      tag = "BLUE"
      map = inaviMap
    }

    inaviMap.setOnMapClickListener { _, coords ->
      infoWindow.position = coords
      if (!infoWindow.isAttached) {
        infoWindow.map = inaviMap
      }
    }

    use_custom_info_window_adapter.setOnClickListener { v ->
      if (v is Checkable) {
        val checked = v.isChecked
        v.isChecked = !checked
        infoWindow.adapter = when (!checked) {
          true -> customAdapter
          else -> defaultAdapter
        }
      }
    }
  }

  private class InfoWindowAdapter(private val context: Context) : InvInfoWindowTextAdapter(context) {

    override fun getText(infoWindow: InvInfoWindow): CharSequence {
      val marker = infoWindow.marker
      return when (marker != null) {
        true -> context.getString(R.string.inv_format_info_window_on_marker, marker.tag.toString())
        else -> context.getString(R.string.inv_format_info_window_on_map, infoWindow.position.latitude, infoWindow.position.longitude)
      }
    }
  }

  private class CustomInfoWindowViewAdapter(private val context: Context) : InvInfoWindowViewAdapter() {

    private val holder: ViewHolder by lazy {
      ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_custom_infowindow, null))
    }

    override fun getView(infoWindow: InvInfoWindow): View {
      val marker = infoWindow.marker
      when (marker != null) {
          true -> {
            holder.icon?.setImageResource(R.drawable.ic_place_black_24dp)
            holder.textView?.text = marker.tag.toString()
          }
          else -> {
            holder.icon?.setImageResource(R.drawable.ic_my_location_black_24dp)
            holder.textView?.text = context.getString(R.string.inv_format_custom_info_window_on_map, infoWindow.position.latitude, infoWindow.position.longitude)
          }
      }

      return holder.view
    }

    private class ViewHolder(val view: View) {
      val icon: ImageView? = view.findViewById(R.id.custom_info_window_icon)
      val textView: TextView? = view.findViewById(R.id.custom_info_window_text)
    }
  }
}