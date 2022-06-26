package com.ruben.feedback.ui

import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.resources.R
import com.ruben.feedback.databinding.FragmentFeedbackSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackSuccessFragment : BaseFragment<FragmentFeedbackSuccessBinding, FeedbackViewModel>() {
  override val binding: FragmentFeedbackSuccessBinding by viewBinding(FragmentFeedbackSuccessBinding::inflate)
  override val viewModel: FeedbackViewModel by viewModels()

  override fun initToolbar() {
    parentActivity?.run {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_square)
    }
    setHasOptionsMenu(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  companion object {
    fun newInstance() = FeedbackSuccessFragment()
  }
}