package backbase.assignment.ui.citymap

import android.os.Bundle
import android.os.Parcelable
import backbase.assignment.R.string
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
    val params = CityMapFragmentArgs.fromBundle(arguments).params
    googleMap.apply {
      addMarker(
        MarkerOptions()
          .position(LatLng(params.lat, params.lng))
          .title(this@CityMapFragment.getString(string.map_marker_title, params.city))
      )
      moveCamera(
        CameraUpdateFactory.newLatLngZoom(LatLng(params.lat, params.lng), cityZoom)
      )
    }
  }

}

@Parcelize
data class MapParams(val city: String, val lat: Double, val lng: Double) : Parcelable

fun MapParams.toBundle() = Bundle().apply { putParcelable("map_params", this@toBundle) }
private fun Bundle.toMapParams() = this.getParcelable<MapParams>("map_params")
