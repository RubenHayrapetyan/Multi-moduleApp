package com.ruben.feedback.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.recyclerview.OnItemClickListener
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.feedback.component.FeedbackIconsAdapter
import com.ruben.feedback.databinding.FragmentFeedbackIconsBinding
import com.ruben.resources.R
import dagger.hilt.android.AndroidEntryPoint
import com.ruben.feedback.util.getEachFeedbackItemSize

@AndroidEntryPoint
class FeedbackIconsFragment : BaseFragment<FragmentFeedbackIconsBinding, FeedbackViewModel>() {
  override val binding: FragmentFeedbackIconsBinding by viewBinding(FragmentFeedbackIconsBinding::inflate)
  override val viewModel: FeedbackViewModel by viewModels()

  override fun initToolbar() {
    parentActivity?.run {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }
    setHasOptionsMenu(true)
  }

  private fun initEmojisRecyclerView() = with(binding) {
    val ctx = context ?: return@with
    val feedbackEmojisAdapter = FeedbackIconsAdapter(itemViewSize = getEachFeedbackItemSize(ctx))
    rvFeedback.adapter = feedbackEmojisAdapter
    feedbackEmojisAdapter.submitList(
      viewModel.createFeedbackThreeEmojis(
        context = ctx,
        isSelectedId = -1
      )
    )

    feedbackEmojisAdapter.onItemClickListener = OnItemClickListener { selectedRateNumber, _ ->
      feedbackEmojisAdapter.submitList(
        viewModel.createFeedbackFiveEmojis(
          context = ctx,
          isSelectedId = selectedRateNumber
        )
      )
    }
  }

  private fun initStarsRecyclerView() = with(binding) {
    val feedbackStarsAdapter =
      FeedbackIconsAdapter(itemViewSize = getEachFeedbackItemSize(context = context ?: return@with))
    rvFeedback.adapter = feedbackStarsAdapter

    feedbackStarsAdapter.submitList(viewModel.createFeedbackStars(isSelectedId = -1))

    feedbackStarsAdapter.onItemClickListener = OnItemClickListener { selectedStarId, position ->
      feedbackStarsAdapter.submitList(
        viewModel.createFeedbackStars(
          isSelectedId = selectedStarId
        )
      )
    }
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
    fun newInstance() = FeedbackIconsFragment()
  }
}