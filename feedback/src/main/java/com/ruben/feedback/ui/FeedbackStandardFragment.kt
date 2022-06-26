package com.ruben.feedback.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.resources.R
import com.ruben.feedback.databinding.FragmentFeedbackStandardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackStandardFragment :
  BaseFragment<FragmentFeedbackStandardBinding, FeedbackViewModel>() {
  override val binding: FragmentFeedbackStandardBinding by viewBinding(
    FragmentFeedbackStandardBinding::inflate
  )
  override val viewModel: FeedbackViewModel by viewModels()

  override fun initToolbar() {
    parentActivity?.run {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }
    setHasOptionsMenu(true)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_feedback, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
      }
      R.id.action_i_refuse -> {

        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  companion object {
    fun newInstance() = FeedbackStandardFragment()
  }
}