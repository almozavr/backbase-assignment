package backbase.assignment.ui.citymap

import android.os.Bundle
import android.os.Parcelable
import backbase.assignment.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.parcel.Parcelize


class CityMapFragment : SupportMapFragment(), OnMapReadyCallback {

  private val cityZoom = 12f

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    getMapAsync(this)
  }

  override fun onMapReady(googleMap: GoogleMap) {
    arguments?.toMapParams()?.also {
      val marker = MarkerOptions()
        .position(LatLng(it.lat, it.lng))
        .title(getString(R.string.map_marker_title, it.city))
      googleMap.addMarker(marker)
      googleMap.moveCamera(
        CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lng), cityZoom)
      )
    }
  }

}

@Parcelize
data class MapParams(val city: String, val lat: Double, val lng: Double) : Parcelable

fun MapParams.toBundle() = Bundle().apply { putParcelable("map_params", this@toBundle) }
private fun Bundle.toMapParams() = this.getParcelable<MapParams>("map_params")
