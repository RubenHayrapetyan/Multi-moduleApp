package com.ruben.location.ui.component

import androidx.recyclerview.widget.DiffUtil
import com.ruben.entity.local.Country

class CountriesDiffUtil : DiffUtil.ItemCallback<Country>() {
  override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
    oldItem == newItem
}
