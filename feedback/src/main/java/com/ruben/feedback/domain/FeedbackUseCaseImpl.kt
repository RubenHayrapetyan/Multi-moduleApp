package com.ruben.feedback.domain

import android.content.Context
import com.ruben.entity.local.Feedback
import com.ruben.entity.local.SeekbarNumber
import com.ruben.feedback.data.FeedbackRepository
import javax.inject.Inject

internal class FeedbackUseCaseImpl @Inject constructor(
  private val feedbackRepository: FeedbackRepository
) : FeedbackUseCase {
  override fun createSeekbarNumbers(
    selectedItemNumber: Int,
    startMarginSize: Int
  ): List<SeekbarNumber> = feedbackRepository.createSeekbarNumbers(
    selectedItemNumber = selectedItemNumber,
    startMarginSize = startMarginSize
  )

  override fun createFeedbackStarts(isSelectedId: Int): List<Feedback.FeedbackStars> =
    feedbackRepository.createFeedbackStars(isSelectedId = isSelectedId)

  override fun createFeedbackThreeEmojis(
    context: Context,
    isSelectedId: Int
  ) = feedbackRepository.createFeedbackThreeEmojis(
    context = context,
    isSelectedId = isSelectedId
  )

  override fun createFeedbackFiveEmojis(
    context: Context,
    isSelectedId: Int
  ) = feedbackRepository.createFeedbackFiveEmojis(
    context = context,
    isSelectedId = isSelectedId
  )
}