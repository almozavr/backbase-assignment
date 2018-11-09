package backbase.assignment.ui.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import backbase.assignment.domain.LocationUseCase.Location

class CityListAdapter : ListAdapter<Location, CityHolder> {

  constructor() : super(object : ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Location, newItem: Location) = true
  })

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(android.R.layout.simple_list_item_1, parent, false)
    return CityHolder(view)
  }

  override fun onBindViewHolder(holder: CityHolder, position: Int) {
    holder.bindTo(getItem(position))
  }

}

class CityHolder(root: View) : ViewHolder(root) {
  private val city by lazy { root.findViewById<TextView>(android.R.id.text1) }
  fun bindTo(item: Location) {
    city.text = "${item.city}, ${item.country}"
  }
}
