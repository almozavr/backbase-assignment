package backbase.assignment.ui.citylist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import backbase.assignment.domain.LocationUserCaseImpl
import backbase.assignment.service.LocationDataSourceImpl

class CityListViewModelFactory(val context: Context) :
  Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val dataSource = LocationDataSourceImpl(context)
    val usecase = LocationUserCaseImpl(dataSource)
    return CityListViewModel(usecase) as T
  }
}
