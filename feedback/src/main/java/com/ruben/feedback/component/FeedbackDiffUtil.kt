package com.ruben.feedback.component

import androidx.recyclerview.widget.DiffUtil
import com.ruben.entity.local.Feedback

class FeedbackDiffUtil : DiffUtil.ItemCallback<Feedback>() {
  override fun areItemsTheSame(oldItem: Feedback, newItem: Feedback): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: Feedback, newItem: Feedback): Boolean =
    oldItem == newItem
}
