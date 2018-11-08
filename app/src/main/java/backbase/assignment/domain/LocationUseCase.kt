package backbase.assignment.domain

import backbase.assignment.domain.LocationUseCase.Location
import backbase.assignment.domain.LocationUseCase.QueryParams
import backbase.assignment.service.LocationDataSource


interface LocationUseCase {
  suspend fun query(params: QueryParams): Collection<Location>

  data class QueryParams(val city: String?)
  data class Location(val city: String, val country: String, val lat: Double, val lng: Double)
}

class LocationUserCaseImpl(private val locationSource: LocationDataSource) : LocationUseCase {
  override suspend fun query(params: QueryParams): Collection<Location> =
    when (params.city) {
      null -> locationSource.data
      else -> locationSource.data.filter { it.name.startsWith(params.city, true) }
    }.map { Location(it.name, it.country, it.coord.lat, it.coord.lon) }

}
