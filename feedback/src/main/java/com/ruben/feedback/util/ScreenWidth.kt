package com.ruben.feedback.util

import android.content.Context
import com.ruben.extentions.getDimenFromRes
import com.ruben.resources.R

fun getEachFeedbackItemSize(context: Context): Int {
  val deviceWidth = context.resources.displayMetrics.widthPixels
  val layoutLeftRightPaddings = context.getDimenFromRes(R.dimen.feedback_layout).toInt()
  val dp12 = context.getDimenFromRes(R.dimen.margin_between_items).toInt()
  val marginBetweenItems = dp12 * 5
  return (deviceWidth - layoutLeftRightPaddings - marginBetweenItems) / 5
}

fun seekbarNumbersWidth(context: Context): Int {
  val deviceWidth = context.resources.displayMetrics.widthPixels
  val numberWidth = context.getDimenFromRes(R.dimen.seekbar_number_width).toInt()
  val layoutLeftRightPaddings = context.getDimenFromRes(R.dimen.feedback_layout).toInt()
  val itemSize = (numberWidth) * 11
  return (deviceWidth - layoutLeftRightPaddings - itemSize) / 11
}