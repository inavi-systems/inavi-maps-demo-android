package com.inavi.maps.androiddemo.activity

import android.app.AlertDialog
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.inavi.maps.androiddemo.R
import com.inavi.mapsdk.geometry.*
import com.inavi.mapsdk.maps.InaviMap
import kotlinx.android.synthetic.main.activity_coordinates_conversion.*

class CoordinatesConversionActivity : InvMapFragmentActivity(R.layout.activity_coordinates_conversion) {

    private val crosshairPoint = PointF()
    private var currentPosition = LatLng.INVALID
    private var inaviMap: InaviMap? = null

    private val ivProjectionCrosshair: View by lazy {
        iv_projection_cross
    }

    private val tvCoordsInfoWgs84: TextView by lazy {
        tv_coords_wgs84_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bt_coordinates_conversion.setOnClickListener {
            val wgs84 = currentPosition
            val katec = Katec(wgs84)
            val utmk = Utmk(wgs84)
            val tm = Tm(wgs84)
            val grs80 = Grs80(wgs84)

            val message = """
KATEC 좌표
${"(%.5f, %.5f)".format(katec.x, katec.y)}

UTM-K 좌표
${"(%.5f, %.5f)".format(utmk.x, utmk.y)}

TM 좌표
${"(%.5f, %.5f)".format(tm.x, tm.y)}

GRS80 좌표
${"(%.5f, %.5f)".format(grs80.x, grs80.y)}
"""

            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.inv_text_popup_ok, null)
                .show()
        }
    }

    override fun onMapReady(inaviMap: InaviMap) {
        this.inaviMap = inaviMap

        inaviMap.addOnCameraChangeListener {
            updateProjection()
        }
        updateProjection()
    }

    private fun updateProjection() {
        val map = inaviMap ?: return

        crosshairPoint.set(ivProjectionCrosshair.x + ivProjectionCrosshair.width / 2, ivProjectionCrosshair.y + ivProjectionCrosshair.height / 2)

        currentPosition = map.projection.getLatLngFromPoint(crosshairPoint)
        tvCoordsInfoWgs84.text = getString(R.string.inv_format_misc_coordinates_wgs84, currentPosition.latitude, currentPosition.longitude)
    }
}