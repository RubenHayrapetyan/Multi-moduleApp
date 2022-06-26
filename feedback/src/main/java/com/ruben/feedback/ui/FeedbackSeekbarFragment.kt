package com.ruben.feedback.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.resources.R
import com.ruben.feedback.component.SeekbarNumbersAdapter
import com.ruben.feedback.databinding.FragmentFeedbackSeekbarBinding
import dagger.hilt.android.AndroidEntryPoint
import com.ruben.feedback.util.doOnSeekBarChanged
import com.ruben.feedback.util.seekbarNumbersWidth

@AndroidEntryPoint
class FeedbackSeekbarFragment :
  BaseFragment<FragmentFeedbackSeekbarBinding, FeedbackViewModel>() {
  override val binding: FragmentFeedbackSeekbarBinding by viewBinding(FragmentFeedbackSeekbarBinding::inflate)
  override val viewModel: FeedbackViewModel by viewModels()

  private val seekbarNumbersAdapter = SeekbarNumbersAdapter()

  override fun initToolbar() {
    parentActivity?.run {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }
    setHasOptionsMenu(true)
  }

  override fun initViews() = with(binding) {
    initSeekbarNumbersRecycler()
    sbRate.doOnSeekBarChanged { progress ->
      seekbarNumbersAdapter.submitList(
        viewModel.createSeekbarNumbers(
          selectedItemNumber = progress,
          startMarginSize = seekbarNumbersWidth(context = context ?: return@doOnSeekBarChanged)
        ),
      )
    }
  }

  private fun initSeekbarNumbersRecycler() = with(binding) {
    rvNumbers.setHasFixedSize(true)
    rvNumbers.adapter = seekbarNumbersAdapter

    seekbarNumbersAdapter.onItemClickListener = OnItemClickListener { number, _ ->
      seekbarNumbersAdapter.submitList(
        viewModel.createSeekbarNumbers(
          selectedItemNumber = number,
          startMarginSize = seekbarNumbersWidth(context = context ?: return@OnItemClickListener)
        )
      )
      sbRate.progress = number
    }
    seekbarNumbersAdapter.submitList(
      viewModel.createSeekbarNumbers(
        selectedItemNumber = 0,
        startMarginSize = seekbarNumbersWidth(requireContext())
      )
    )
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_feedback, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
      R.id.action_i_refuse -> {

        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  companion object {
    fun newInstance() = FeedbackSeekbarFragment()
  }
}