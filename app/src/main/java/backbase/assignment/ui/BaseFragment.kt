package backbase.assignment.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KProperty

abstract class BaseFragment<V : ViewModel> : Fragment() {

  protected abstract val viewModel: V

}

inline fun <reified V : ViewModel> BaseFragment<V>.factoryViewModel(noinline factory: ViewModelFactory<V>) =
  ViewModelDelegate<V>(this, V::class.java, factory)

class ViewModelDelegate<out V : ViewModel>(
  private val fragment: Fragment,
  private val clazz: Class<V>,
  private val factory: ViewModelFactory<V>
) {

  operator fun getValue(thisRef: Any, prop: KProperty<*>): V =
    ViewModelProviders.of(
      fragment,
      object : Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
          factory.invoke() as T
      }).get(clazz)

}

typealias ViewModelFactory<V> = () -> V
