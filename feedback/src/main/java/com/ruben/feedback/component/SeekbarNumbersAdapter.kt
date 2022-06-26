package com.ruben.feedback.component

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.AsyncDifferConfig
import com.ruben.components_ui.base.BaseRecyclerViewAdapter
import com.ruben.components_ui.base.BaseViewHolder
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.entity.local.SeekbarNumber
import com.ruben.extentions.getColorFromRes
import com.ruben.extentions.layoutInflater
import com.ruben.resources.R
import com.ruben.feedback.databinding.ItemSeekbarNumberBinding

class SeekbarNumbersAdapter :
  BaseRecyclerViewAdapter<ItemSeekbarNumberBinding, SeekbarNumber, SeekbarNumbersAdapter.SeekbarNumbersViewHolder>(
    AsyncDifferConfig.Builder(SeekbarNumbersDiffUtil())
  ) {

  var onItemClickListener: OnItemClickListener<Int>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeekbarNumbersViewHolder =
    SeekbarNumbersViewHolder(
      binding = ItemSeekbarNumberBinding.inflate(parent.layoutInflater, parent, false),
      onItemClickListener = onItemClickListener
    )

  class SeekbarNumbersViewHolder(
    private val binding: ItemSeekbarNumberBinding,
    private val onItemClickListener: OnItemClickListener<Int>?
  ) : BaseViewHolder<ItemSeekbarNumberBinding, SeekbarNumber>(binding = binding) {
    override fun bind(item: SeekbarNumber) = with(binding) {
      val greyColor = root.context.getColorFromRes(R.color.seekbar_number_grey)
      val greenColor = root.context.getColorFromRes(R.color.e1_sky)
      val numberColor = if (item.isSelected) greenColor else greyColor

      tvNumber.run {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
          setMargins(item.startMarginSize, 0, 0, 0)
        }
        setTextColor(numberColor)
        text = item.number.toString()
        setOnClickListener {
          onItemClickListener?.onItemClick(item.number, adapterPosition)
        }
      }
    }
  }
}