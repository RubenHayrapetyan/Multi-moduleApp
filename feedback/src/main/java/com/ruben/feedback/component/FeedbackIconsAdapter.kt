package com.ruben.feedback.component

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.viewbinding.ViewBinding
import com.ruben.components_ui.base.BaseRecyclerViewAdapter
import com.ruben.components_ui.base.BaseViewHolder
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.entity.local.Feedback
import com.ruben.entity.local.FeedbackType
import com.ruben.extentions.layoutInflater
import com.ruben.feedback.databinding.ItemFeedbackEmojiBinding
import com.ruben.feedback.databinding.ItemFeedbackStarBinding
import com.ruben.resources.R

class FeedbackIconsAdapter(private val itemViewSize: Int) :
  BaseRecyclerViewAdapter<ViewBinding, Feedback,
      FeedbackIconsAdapter.FeedbackViewHolder<ViewBinding, Feedback>>(
    AsyncDifferConfig.Builder(FeedbackDiffUtil())
  ) {
  var onItemClickListener: OnItemClickListener<Int>? = null

  override fun getItemViewType(position: Int): Int = when (getItem(position)) {
    is Feedback.FeedbackStars -> FeedbackType.STARS.ordinal
    is Feedback.FeedbackEmojis -> FeedbackType.EMOJIS.ordinal
  }

  @Suppress("UNCHECKED_CAST")
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FeedbackViewHolder<ViewBinding, Feedback> =
    when (viewType) {
      FeedbackType.STARS.ordinal ->
        FeedbackStarsViewHolder(
          binding = ItemFeedbackStarBinding.inflate(parent.layoutInflater, parent, false),
          itemSize = itemViewSize,
          onItemClickListener = onItemClickListener
        )

      FeedbackType.EMOJIS.ordinal ->
        FeedbackEmojisViewHolder(
          binding = ItemFeedbackEmojiBinding.inflate(parent.layoutInflater, parent, false),
          itemSize = itemViewSize,
          onItemClickListener = onItemClickListener
        )

      else -> throw IllegalArgumentException("Wrong feedback type")
    } as FeedbackViewHolder<ViewBinding, Feedback>

  abstract class FeedbackViewHolder<Binding : ViewBinding, Item : Feedback>(binding: Binding) :
    BaseViewHolder<Binding, Item>(binding = binding)

  class FeedbackStarsViewHolder(
    private val binding: ItemFeedbackStarBinding,
    private val itemSize: Int,
    private val onItemClickListener: OnItemClickListener<Int>?
  ) : FeedbackViewHolder<ItemFeedbackStarBinding, Feedback.FeedbackStars>(binding = binding) {

    init {
      itemView.updateLayoutParams {
        width = itemSize
        height = itemSize
      }
    }

    override fun bind(item: Feedback.FeedbackStars) = with(binding) {
      val starIconRes = if (item.feedbackStarsIsSelected) R.drawable.ic_yellow_star else R.drawable.ic_blue_star
      ivStar.setImageResource(starIconRes)

      ivStar.setOnClickListener {
        onItemClickListener?.onItemClick(item.feedbackStarsId, adapterPosition)
      }
    }
  }

  class FeedbackEmojisViewHolder(
    private val binding: ItemFeedbackEmojiBinding,
    private val itemSize: Int,
    private val onItemClickListener: OnItemClickListener<Int>?
  ) : FeedbackViewHolder<ItemFeedbackEmojiBinding, Feedback.FeedbackEmojis>(binding = binding) {

    init {
      binding.ivEmoji.updateLayoutParams {
        width = itemSize
        height = itemSize
      }
    }

    override fun bind(item: Feedback.FeedbackEmojis) = with(binding) {
      val selectedEmojiBg = R.drawable.bg_emoji_selected
      val unselectedEmojiBg = R.drawable.bg_emoji_unselected

      ivEmoji.setImageResource(item.emoji.icon)
      tvRateStatus.text = item.emoji.text

      if (item.feedbackEmojisIsSelected) {
        ivEmoji.setBackgroundResource(selectedEmojiBg)
      } else {
        ivEmoji.setBackgroundResource(unselectedEmojiBg)
      }

      itemView.setOnClickListener {
        onItemClickListener?.onItemClick(item.feedbackEmojisId, adapterPosition)
      }
    }
  }
}