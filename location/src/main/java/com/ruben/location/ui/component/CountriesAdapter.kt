package com.ruben.location.ui.component

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import com.ruben.components_ui.base.BaseRecyclerViewAdapter
import com.ruben.components_ui.base.BaseViewHolder
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.entity.local.Country
import com.ruben.extentions.layoutInflater
import com.ruben.location.databinding.ItemCountryBinding

class CountriesAdapter(private val itemClickListener: OnItemClickListener<Country>) :
  BaseRecyclerViewAdapter<ItemCountryBinding, Country, CountriesAdapter.CountriesViewHolder>(
    AsyncDifferConfig.Builder(CountriesDiffUtil())
  ) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder =
    CountriesViewHolder(
      binding = ItemCountryBinding.inflate(parent.layoutInflater, parent, false),
      itemClickListener = itemClickListener
    )

  class CountriesViewHolder(
    private val binding: ItemCountryBinding,
    private val itemClickListener: OnItemClickListener<Country>,
  ) : BaseViewHolder<ItemCountryBinding, Country>(binding = binding) {
    override fun bind(item: Country) {
      binding.tvCountry.text = item.name
      binding.tvCountry.setOnClickListener { itemClickListener.onItemClick(item = item, position = adapterPosition) }
    }
  }
}
