package com.ruben.entity.local

sealed class Feedback(
  val id: Int,
  val type: FeedbackType
) {
  data class FeedbackStars(
    val feedbackStarsId: Int,
    var feedbackStarsIsSelected: Boolean
  ) : Feedback(feedbackStarsId, FeedbackType.STARS)

  data class FeedbackEmojis(
    val feedbackEmojisId: Int,
    var feedbackEmojisIsSelected: Boolean,
    val emoji: Emoji
  ) : Feedback(feedbackEmojisId, FeedbackType.EMOJIS)
}

enum class FeedbackType {
  STARS,
  EMOJIS
}