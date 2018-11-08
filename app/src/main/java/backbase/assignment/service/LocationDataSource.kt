package backbase.assignment.service

import android.content.Context
import backbase.assignment.R
import backbase.assignment.service.LocationDataSource.Location
import com.google.gson.Gson


interface LocationDataSource {
  val data: Collection<Location>

  data class Location(val name: String, val country: String, val coord: Coordinates)
  data class Coordinates(val lat: Double, val lon: Double)
}

class LocationDataSourceImpl(context: Context) : LocationDataSource {

  override val data: Collection<Location> by lazy {
    val assetsReader = context.resources.openRawResource(R.raw.cities).bufferedReader()
    Gson().newBuilder().create().fromJson(assetsReader, Array<Location>::class.java).toList()
  }

}

