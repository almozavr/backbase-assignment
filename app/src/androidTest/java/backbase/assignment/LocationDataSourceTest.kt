package backbase.assignment

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import backbase.assignment.service.LocationDataSource
import backbase.assignment.service.LocationDataSourceImpl
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationDataSourceTest {

  private val source: LocationDataSource

  init {
    source = LocationDataSourceImpl(InstrumentationRegistry.getTargetContext())
  }

  @Test
  fun test_data_source_is_loaded_and_converted() {
    assert(source.data.isEmpty().not())
  }

}
