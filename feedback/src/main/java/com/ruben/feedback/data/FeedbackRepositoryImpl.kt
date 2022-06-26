package com.ruben.feedback.data

import android.content.Context
import com.ruben.entity.local.Emoji
import com.ruben.entity.local.Feedback
import com.ruben.entity.local.SeekbarNumber
import com.ruben.resources.R
import javax.inject.Inject

internal class FeedbackRepositoryImpl @Inject constructor() : FeedbackRepository {

  override fun createSeekbarNumbers(
    selectedItemNumber: Int,
    startMarginSize: Int
  ) = buildList {
    for (i in 0..10) {
      if (selectedItemNumber == i) {
        add(SeekbarNumber(i + 1, i, startMarginSize, true))
      } else {
        add(SeekbarNumber(i + 1, i, startMarginSize, false))
      }
    }
  }

  override fun createFeedbackStars(isSelectedId: Int): List<Feedback.FeedbackStars> = buildList {
    for (i in 1..5) {
      if (isSelectedId < i) {
        add(Feedback.FeedbackStars(i, false))
      } else add(Feedback.FeedbackStars(i, true))
    }
  }

  override fun createFeedbackThreeEmojis(
    context: Context,
    isSelectedId: Int
  ) = buildList {
    add(
      index = 0,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 0,
        feedbackEmojisIsSelected = isSelectedId == 0,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_bad,
          text = context.resources.getString(R.string.text_rate_bad),
        )
      )
    )
    add(
      index = 1,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 1,
        feedbackEmojisIsSelected = isSelectedId == 1,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_normal,
          text = context.resources.getString(R.string.text_rate_normal),
        )
      )
    )
    add(
      index = 2,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 2,
        feedbackEmojisIsSelected = isSelectedId == 2,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_good,
          text = context.resources.getString(R.string.text_rate_good),
        )
      )
    )
  }

  override fun createFeedbackFiveEmojis(
    context: Context,
    isSelectedId: Int
  ) = buildList {
    add(
      index = 0,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 0,
        feedbackEmojisIsSelected = isSelectedId == 0,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_very_bad,
          text = context.resources.getString(R.string.text_rate_very_bad),
        )
      )
    )
    add(
      index = 1,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 1,
        feedbackEmojisIsSelected = isSelectedId == 1,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_bad,
          text = context.resources.getString(R.string.text_rate_bad),
        )
      )
    )
    add(
      index = 2,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 2,
        feedbackEmojisIsSelected = isSelectedId == 2,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_normal,
          text = context.resources.getString(R.string.text_rate_normal),
        )
      )
    )
    add(
      index = 3,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 3,
        feedbackEmojisIsSelected = isSelectedId == 3,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_good,
          text = context.resources.getString(R.string.text_rate_good),
        )
      )
    )
    add(
      index = 4,
      element = Feedback.FeedbackEmojis(
        feedbackEmojisId = 4,
        feedbackEmojisIsSelected = isSelectedId == 4,
        emoji = Emoji(
          icon = R.drawable.ic_emoji_very_good,
          text = context.resources.getString(R.string.text_rate_very_good),
        )
      )
    )
  }
}