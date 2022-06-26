package com.ruben.feedback.component

import androidx.recyclerview.widget.DiffUtil
import com.ruben.entity.local.SeekbarNumber

class SeekbarNumbersDiffUtil : DiffUtil.ItemCallback<SeekbarNumber>() {
  override fun areItemsTheSame(oldItem: SeekbarNumber, newItem: SeekbarNumber): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: SeekbarNumber, newItem: SeekbarNumber): Boolean =
    oldItem == newItem
}