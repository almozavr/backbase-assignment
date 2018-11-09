package backbase.assignment.domain

import backbase.assignment.AppScope
import backbase.assignment.domain.LocationUseCase.Location
import backbase.assignment.domain.LocationUseCase.QueryParams
import backbase.assignment.service.LocationDataSource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


interface LocationUseCase {
  fun query(params: QueryParams): Deferred<List<Location>>

  data class QueryParams(val city: String?)
  data class Location(val id: Int, val city: String, val country: String, val lat: Double, val lng: Double)
}

class LocationUserCaseImpl(
  private val locationSource: LocationDataSource
) : LocationUseCase {

  override fun query(params: QueryParams): Deferred<List<Location>> =
    AppScope.async(Dispatchers.IO) {
      when (params.city) {
        null -> locationSource.data
        else -> locationSource.data.filter { it.name.startsWith(params.city, true) }
      }.map { Location(it._id, it.name, it.country, it.coord.lat, it.coord.lon) }
    }

}
