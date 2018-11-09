package backbase.assignment.ui.citymap

import android.os.Bundle
import android.os.Parcelable
import backbase.assignment.domain.LocationUseCase.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.parcel.Parcelize


class CityMapFragment : SupportMapFragment(), OnMapReadyCallback {

  companion object {

    fun newInstance(location: Location): CityMapFragment {
      val fragment = CityMapFragment()
      fragment.arguments = Bundle().apply {
        putParcelable(
          ARG_KEY,
          MapLocation(location.city, location.lat, location.lng)
        )
      }
      return fragment
    }
  }

  private val cityZoom = 12f

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    getMapAsync(this)
  }

  override fun onMapReady(googleMap: GoogleMap) {
    arguments?.getParcelable<MapLocation>(ARG_KEY)?.also {
      val marker = MarkerOptions()
        .position(LatLng(it.lat, it.lng))
        .title("Marker in ${it.city}")
      googleMap.addMarker(marker)
      googleMap.moveCamera(
        CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lng), cityZoom)
      )
    }
  }

}

const val ARG_KEY: String = "location"

@Parcelize
data class MapLocation(val city: String, val lat: Double, val lng: Double) : Parcelable
