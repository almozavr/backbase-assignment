package backbase.assignment.ui.citydetails

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import backbase.assignment.R
import backbase.assignment.ui.citymap.MapParams
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.city_details_fragment.city_details_container
import kotlinx.android.synthetic.main.city_details_fragment.city_details_coords
import kotlinx.android.synthetic.main.city_details_fragment.city_details_name
import kotlinx.android.synthetic.main.city_details_fragment.city_details_picture
import kotlinx.android.synthetic.main.city_details_motion_end.city_details_description


class CityDetailsFragment : Fragment() {

  lateinit var params: DetailsParams
  var introPlayed = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    params = CityDetailsFragmentArgs.fromBundle(arguments).details
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.city_details_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    setHasOptionsMenu(true)
    params.apply {
      city_details_picture.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.city))
      city_details_name.text = city
      city_details_coords.text = "$lat, $lng"
      city_details_description.text = (1..20).joinToString(separator = ", ") { city }
    }
    if (introPlayed.not()) {
      city_details_container.transitionToEnd()
      introPlayed = true
    } else {
      city_details_container.setState(R.id.end, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.details_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.show_map -> {
        showMap(params)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun showMap(it: DetailsParams) {
    (activity as NavHost).navController.navigate(
      CityDetailsFragmentDirections.showMap(MapParams(it.city, it.lat, it.lng))
    )
  }

}

@Parcelize
data class DetailsParams(val city: String, val lat: Double, val lng: Double) : Parcelable
