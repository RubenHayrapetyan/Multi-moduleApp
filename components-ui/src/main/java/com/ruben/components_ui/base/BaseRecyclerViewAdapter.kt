package com.ruben.components_ui.base

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<Binding : ViewBinding, Item, ViewHolder :
BaseViewHolder<Binding, Item>>(diffCallback: AsyncDifferConfig.Builder<Item>) :
  ListAdapter<Item, ViewHolder>(diffCallback.build()) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(item = getItem(position))
}
