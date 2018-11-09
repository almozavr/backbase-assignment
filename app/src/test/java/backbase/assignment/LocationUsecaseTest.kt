package backbase.assignment

import backbase.assignment.domain.LocationUseCase
import backbase.assignment.domain.LocationUseCase.QueryParams
import backbase.assignment.domain.LocationUserCaseImpl
import backbase.assignment.service.LocationDataSource
import backbase.assignment.service.LocationDataSource.Coordinates
import backbase.assignment.service.LocationDataSource.Location
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LocationUsecaseTest {

  private val source: LocationDataSource
  private val usecase: LocationUseCase

  init {
    source = object : LocationDataSource {
      override val data: Collection<Location> = listOf(
        Location(0, "Dnipro", "", Coordinates(0.0, 0.0)),
        Location(0, "Kyiv", "", Coordinates(0.0, 0.0)),
        Location(0, "Lviv", "", Coordinates(0.0, 0.0)),
        Location(0, "Dniprorudne", "", Coordinates(0.0, 0.0))
      )
    }
    usecase = LocationUserCaseImpl(source)
  }

  @Test
  fun `test query with empty params returns all locations`() {
    runBlocking {
      usecase.query(QueryParams(null)).await()
    }.also {
      assert(it.size == source.data.size)
    }
  }

  @Test
  fun `test query by city name filters locations`() {
    runBlocking {
      usecase.query(QueryParams("dnipro")).await()
    }.also {
      assert(it.size == 2)
      assert(it.all { location -> location.city == "Dnipro" || location.city == "Dniprorudne" })
    }
  }

  @Test
  fun `test query by city name filters locations to empty if no matches`() {
    runBlocking {
      usecase.query(QueryParams("xxx")).await()
    }.also {
      assert(it.isEmpty())
    }
  }
}
