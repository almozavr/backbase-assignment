package backbase.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import backbase.assignment.domain.LocationUseCase.Location
import backbase.assignment.ui.citylist.CityListFragment
import backbase.assignment.ui.citymap.CityMapFragment

class MainActivity : AppCompatActivity(), Navigator {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    supportFragmentManager.apply {
      setupHomeAsBack(this@MainActivity)
      addOnBackStackChangedListener {
        setupHomeAsBack(this@MainActivity)
      }
    }
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, CityListFragment.newInstance())
        .commitNow()
    }
  }

  override fun showDetails(location: Location) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.container, CityMapFragment.newInstance(location))
      .addToBackStack(null)
      .commit()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

}

private fun FragmentManager.setupHomeAsBack(activity: AppCompatActivity) {
  val homeAsBack = backStackEntryCount > 0
  activity.supportActionBar?.setDisplayHomeAsUpEnabled(homeAsBack)
  activity.supportActionBar?.setDisplayShowHomeEnabled(homeAsBack)
}

