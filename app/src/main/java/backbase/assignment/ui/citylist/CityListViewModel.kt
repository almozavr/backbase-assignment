package backbase.assignment.ui.citylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import backbase.assignment.domain.LocationUseCase
import backbase.assignment.domain.LocationUseCase.Location
import backbase.assignment.domain.LocationUseCase.QueryParams
import backbase.assignment.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityListViewModel(private val usecase: LocationUseCase) : BaseViewModel() {

  private val _cities: MutableLiveData<List<Location>> = MutableLiveData()
  val cities: LiveData<List<Location>> = _cities

  fun search(city: String?) {
    launch(Dispatchers.Main) {
      val citiesList = usecase.query(QueryParams(city)).await().sortedBy { it.city }
      _cities.value = citiesList
    }
  }

}
