package com.ruben.components_ui.recyclerview

fun interface OnItemClickListener<I> {
  fun onItemClick(item: I, position: Int)
}
