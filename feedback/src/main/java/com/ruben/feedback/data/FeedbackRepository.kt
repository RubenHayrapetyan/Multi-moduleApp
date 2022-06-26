package com.ruben.feedback.data

import android.content.Context
import com.ruben.entity.local.Feedback
import com.ruben.entity.local.SeekbarNumber

interface FeedbackRepository {
  fun createSeekbarNumbers(selectedItemNumber: Int, startMarginSize: Int): List<SeekbarNumber>

  fun createFeedbackStars(isSelectedId: Int): List<Feedback.FeedbackStars>

  fun createFeedbackThreeEmojis(
    context: Context,
    isSelectedId: Int
  ): List<Feedback.FeedbackEmojis>

  fun createFeedbackFiveEmojis(
    context: Context,
    isSelectedId: Int
  ): List<Feedback.FeedbackEmojis>
}