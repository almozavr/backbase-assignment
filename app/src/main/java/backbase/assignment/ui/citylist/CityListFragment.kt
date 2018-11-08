package backbase.assignment.ui.citylist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import backbase.assignment.R

class CityListFragment : Fragment() {

  companion object {
    fun newInstance() = CityListFragment()
  }

  private lateinit var viewModel: CityListViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.city_list_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(CityListViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
