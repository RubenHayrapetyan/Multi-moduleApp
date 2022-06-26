package com.ruben.feedback.domain

import android.content.Context
import com.ruben.entity.local.Feedback
import com.ruben.entity.local.SeekbarNumber

interface FeedbackUseCase {

  fun createSeekbarNumbers(selectedItemNumber: Int, startMarginSize: Int): List<SeekbarNumber>

  fun createFeedbackStarts(isSelectedId: Int): List<Feedback.FeedbackStars>

  fun createFeedbackThreeEmojis(
    context: Context,
    isSelectedId: Int
  ): List<Feedback.FeedbackEmojis>

  fun createFeedbackFiveEmojis(
    context: Context,
    isSelectedId: Int
  ): List<Feedback.FeedbackEmojis>
}