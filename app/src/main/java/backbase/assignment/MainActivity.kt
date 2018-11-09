package backbase.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import backbase.assignment.ui.citylist.CityListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, CityListFragment.newInstance())
        .commitNow()
    }
  }

}
