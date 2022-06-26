package com.ruben.feedback.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ruben.feedback.ui.FeedbackIconsFragment
import com.ruben.feedback.ui.FeedbackSeekbarFragment
import com.ruben.feedback.ui.FeedbackStandardFragment
import com.ruben.feedback.ui.FeedbackSuccessFragment

fun feedbackIconsScreen() = FragmentScreen {
  FeedbackIconsFragment.newInstance()
}

fun feedbackSeekbarScreen() = FragmentScreen {
  FeedbackSeekbarFragment.newInstance()
}

fun feedbackStandardScreen() = FragmentScreen {
  FeedbackStandardFragment.newInstance()
}

fun feedbackSuccessScreen() = FragmentScreen {
  FeedbackSuccessFragment.newInstance()
}