package com.ruben.location.ui.component

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import com.ruben.components_ui.base.BaseRecyclerViewAdapter
import com.ruben.components_ui.base.BaseViewHolder
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.entity.local.City
import com.ruben.extentions.getDrawableFromRes
import com.ruben.extentions.layoutInflater
import com.ruben.resources.R
import com.ruben.location.databinding.ItemCityBinding

class CitiesAdapter(private val itemClickListener: OnItemClickListener<City>) :
  BaseRecyclerViewAdapter<ItemCityBinding, City, CitiesAdapter.CitiesViewHolder>(
    AsyncDifferConfig.Builder(CitiesDiffUtil())
  ) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
    CitiesViewHolder(
      binding = ItemCityBinding.inflate(parent.layoutInflater, parent, false),
      itemClickListener = itemClickListener,
    )

  class CitiesViewHolder(
    private val binding: ItemCityBinding,
    private val itemClickListener: OnItemClickListener<City>,
  ) : BaseViewHolder<ItemCityBinding, City>(binding = binding) {
    override fun bind(item: City) = with(binding){
      val (cityName, selectedItem) = if (item == City.EMPTY) {
        itemView.context.getString(R.string.select_all) to null
      } else {
        item.name to item
      }
      tvCity.text = cityName
      tvCity.setOnClickListener {
        tvCity.setCompoundDrawablesRelativeWithIntrinsicBounds(
          null,
          null,
          tvCity.context.getDrawableFromRes(drawableResId = R.drawable.ic_tick_square),
          null,
        )
        selectedItem?.let { city->
          itemClickListener.onItemClick(item = city, position = adapterPosition)
        }
      }
    }
  }
}
