package com.ruben.location.ui.component

import androidx.recyclerview.widget.DiffUtil
import com.ruben.entity.local.City

class CitiesDiffUtil : DiffUtil.ItemCallback<City>() {
  override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
    oldItem == newItem
}
