package backbase.assignment.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.navigation.NavHost
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import backbase.assignment.R
import backbase.assignment.domain.LocationUseCase.Location
import backbase.assignment.domain.LocationUserCaseImpl
import backbase.assignment.service.LocationDataSourceImpl
import backbase.assignment.ui.BaseFragment
import backbase.assignment.ui.citymap.MapParams
import backbase.assignment.ui.citymap.toBundle
import backbase.assignment.ui.factoryViewModel

class CityListFragment : BaseFragment<CityListViewModel>() {

  override val viewModel: CityListViewModel by factoryViewModel {
    val dataSource = LocationDataSourceImpl(context!!)
    val usecase = LocationUserCaseImpl(dataSource)
    CityListViewModel(usecase)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.city_list_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupList(view)
    setupSearchView(view)
  }

  private fun setupList(view: View) {
    view.findViewById<RecyclerView>(R.id.citylist).apply {
      layoutManager = LinearLayoutManager(view.context)
      adapter = CityListAdapter { showMap(it) }
      //
      viewModel.cities.observe(this@CityListFragment, Observer { cities ->
        val truncatedCities = cities.take(1000)
        (adapter as CityListAdapter).submitList(truncatedCities)
      })
    }
  }

  private fun setupSearchView(view: View) {
    view.findViewById<SearchView>(R.id.cityfilter).apply {
      setOnQueryTextListener(object : OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
          return this.onQueryTextSubmit(newText)
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
          viewModel.search(query)
          return true
        }
      })
      setOnSearchClickListener { viewModel.search(query.toString()) }
      isIconified = false
    }
  }

  private fun showMap(it: Location) {
    (activity as NavHost).navController.navigate(
      R.id.nav_action_city_list_to_map, MapParams(it.city, it.lat, it.lng).toBundle()
    )
  }

}
