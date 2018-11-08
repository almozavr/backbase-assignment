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
        Location("Dnipro", "", Coordinates(0.0, 0.0)),
        Location("Kyiv", "", Coordinates(0.0, 0.0)),
        Location("Lviv", "", Coordinates(0.0, 0.0)),
        Location("Dniprorudne", "", Coordinates(0.0, 0.0))
      )
    }
    usecase = LocationUserCaseImpl(source)
  }

  @Test
  fun `test query with empty params returns all locations`() {
    runBlocking { usecase.query(QueryParams(null)) }.also {
      assert(it.size == source.data.size)
    }
  }

  @Test
  fun `test query by city name filters locations`() {
    runBlocking { usecase.query(QueryParams("dnipro")) }.also {
      assert(it.size == 2)
      assert(it.all { it.city == "Dnipro" || it.city == "Dniprorudne" })
    }
  }

  @Test
  fun `test query by city name filters locations to empty if no matches`() {
    runBlocking { usecase.query(QueryParams("xxx")) }.also {
      assert(it.isEmpty())
    }
  }
}
