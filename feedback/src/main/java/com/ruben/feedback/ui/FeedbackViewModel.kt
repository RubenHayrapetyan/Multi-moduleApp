package com.ruben.feedback.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.ruben.feedback.domain.FeedbackUseCase
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
  private val feedbackUseCase: FeedbackUseCase
) : ViewModel() {

  fun createFeedbackStars(isSelectedId: Int) =
    feedbackUseCase.createFeedbackStarts(isSelectedId = isSelectedId)

  fun createFeedbackThreeEmojis(
    context: Context,
    isSelectedId: Int
  ) = feedbackUseCase.createFeedbackThreeEmojis(
    context = context,
    isSelectedId = isSelectedId
  )

  fun createFeedbackFiveEmojis(
    context: Context,
    isSelectedId: Int
  ) = feedbackUseCase.createFeedbackFiveEmojis(
    context = context,
    isSelectedId = isSelectedId
  )

  fun createSeekbarNumbers(
    selectedItemNumber: Int,
    startMarginSize: Int
  ) = feedbackUseCase.createSeekbarNumbers(
    selectedItemNumber = selectedItemNumber,
    startMarginSize = startMarginSize
  )
}