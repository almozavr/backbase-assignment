package backbase.assignment.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import backbase.assignment.R


class CityListFragment : Fragment() {

  companion object {
    fun newInstance() = CityListFragment()
  }

  private lateinit var viewModel: CityListViewModel
  private lateinit var citiesAdapter: CityListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this, CityListViewModelFactory(context!!))
      .get(CityListViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.city_list_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<RecyclerView>(R.id.citylist).apply {
      citiesAdapter = CityListAdapter()
      layoutManager = LinearLayoutManager(view.context)
      adapter = citiesAdapter
    }
    view.findViewById<SearchView>(R.id.cityfilter).apply {
      setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
    viewModel.cities.observe(this, Observer { citiesAdapter.submitList(it) })
  }

}
