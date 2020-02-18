package com.inavi.maps.androiddemo.activity

import android.graphics.Color
import android.widget.Toast
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.constants.InvConstants
import com.inavi.mapsdk.geometry.LatLng
import com.inavi.mapsdk.maps.*
import com.inavi.mapsdk.style.clustering.Cluster
import com.inavi.mapsdk.style.clustering.ClusterItem
import com.inavi.mapsdk.style.clustering.ClusterManager
import com.inavi.mapsdk.style.clustering.DefaultClusterIconGenerator
import com.inavi.mapsdk.style.shapes.InvMarkerIcons
import com.inavi.mapsdk.style.shapes.InvMarkerOptions

data class NumberItem(private val position: LatLng, val number: Int) : ClusterItem {
  override fun getPosition() = position
}

class MarkerClusteringActivity : InvMapFragmentActivity(InvMapOptions().camera(INIT_CAMERA).zoomControlVisible(true)),
  ClusterManager.OnRenderListener<NumberItem>, ClusterManager.OnClickListener<NumberItem> {

  private var inaviMap: InaviMap? = null
  private var clusterManager: ClusterManager<NumberItem>? = null

  override fun onMapReady(inaviMap: InaviMap) {
    this.inaviMap = inaviMap

    clusterManager = ClusterManager<NumberItem>(this, inaviMap).also {
      it.clusterIconGenerator = DefaultClusterIconGenerator.withColors(this, BACKGROUND_COLORS, CRITERIA)
      it.onRenderListener = this
      it.onClickListener = this
    }

    val items = arrayListOf<NumberItem>()
    for (i in 1..1000) {
      items.add(NumberItem(generateRandomPosition(), i))
    }

    clusterManager?.addItems(items)
  }

  private fun generateRandomPosition(): LatLng {
    fun randomScale(): Double {
      return (0..Int.MAX_VALUE).random() / Int.MAX_VALUE.toDouble() * 2.0 - 1.0
    }

    val extent = 0.02
    return LatLng(
      POSITION.latitude + extent * randomScale(),
      POSITION.longitude + extent * randomScale()
    )
  }

  companion object {
    private val INIT_CAMERA = CameraPosition(InvConstants.POSITION_INAVI.target, 13.0, 0.0, 0.0)
    private val POSITION = LatLng(37.40219, 127.11077)
    private val BACKGROUND_COLORS = listOf(
      Color.parseColor("#0099cc"),
      Color.parseColor("#669900"),
      Color.parseColor("#ff8800"),
      Color.parseColor("#cc0000"),
      Color.parseColor("#9933cc")
    )
    private val CRITERIA = listOf(10, 50, 100, 200, 500)
    private val MARKER_ICONS = arrayListOf(InvMarkerIcons.RED, InvMarkerIcons.GREEN, InvMarkerIcons.BLUE, InvMarkerIcons.YELLOW, InvMarkerIcons.GRAY)
  }

  override fun onRenderCluster(cluster: Cluster<NumberItem>, markerOptions: InvMarkerOptions) {
    markerOptions.position = cluster.position
  }

  override fun onRenderClusterItem(clusterItem: NumberItem, markerOptions: InvMarkerOptions) {
    markerOptions.position = clusterItem.position;
    markerOptions.iconScale = 0.8f
    markerOptions.iconImage = MARKER_ICONS[clusterItem.number % MARKER_ICONS.size]
    markerOptions.title = "마커 #${clusterItem.number}"
  }

  override fun onClusterClick(cluster: Cluster<NumberItem>, markerOptions: InvMarkerOptions): Boolean {
    val position = cluster.position
    val count = cluster.count
    Toast.makeText(this,
      getString(R.string.inv_format_cluster_click, position.latitude, position.longitude, count),
      Toast.LENGTH_SHORT
    ).show()

    val cameraUpdate = CameraUpdate.targetTo(position)
    cameraUpdate.animationType = CameraAnimationType.Easing
    inaviMap?.moveCamera(cameraUpdate)
    return true
  }

  override fun onClusterItemClick(clusterItem: NumberItem, markerOptions: InvMarkerOptions): Boolean {
    val position = clusterItem.position
    Toast.makeText(this,
      getString(R.string.inv_format_cluster_item_click, position.latitude, position.longitude, clusterItem.number),
      Toast.LENGTH_SHORT
    ).show()

    val cameraUpdate = CameraUpdate.targetTo(position)
    cameraUpdate.animationType = CameraAnimationType.Easing
    inaviMap?.moveCamera(cameraUpdate)
    return true
  }
}