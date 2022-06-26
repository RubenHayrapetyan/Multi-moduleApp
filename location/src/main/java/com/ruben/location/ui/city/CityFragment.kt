package com.ruben.location.ui.city

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.components.attributs.arguments
import com.ruben.components_ui.recyclerview.DividerLineItemDecoration
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.constants.Constants
import com.ruben.extentions.getDrawableFromRes
import com.ruben.extentions.gone
import com.ruben.extentions.visible
import com.ruben.resources.R
import com.ruben.location.databinding.FragmentCityBinding
import com.ruben.location.ui.component.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>() {
  override val binding: FragmentCityBinding by viewBinding(FragmentCityBinding::inflate)
  override val viewModel: CityViewModel by viewModels()

  private val parent: String by arguments(key = Constants.ARG_PARENT, default = Constants.PARENT_HOME)

  private var searchMenu: Menu? = null

  private val citiesAdapter = CitiesAdapter { city, _ ->
    viewModel.notifyCloseLocationPicker(city = city, parent = parent)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    parentActivity?.run {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }
    setHasOptionsMenu(true)
  }

  override fun initViews() = with(binding) {
    etSearchInput.doAfterTextChanged {
      val textInput = it?.toString() ?: ""
      searchMenu?.findItem(R.id.action_clear)?.isVisible = textInput.isNotEmpty()
      viewModel.searchCities(textInput = textInput)
    }
    context?.getDrawableFromRes(R.drawable.bg_divider)?.let { divider ->
      val dividerItemDecoration = DividerLineItemDecoration(divider = divider)
      rvCities.addItemDecoration(dividerItemDecoration)
    }
    rvCities.adapter = citiesAdapter
  }

  override fun initCollectors() = with(viewModel) {
    cities.collectWithLifecycle { cities ->
      citiesAdapter.submitList(cities)
      binding.lvLoading.gone()
    }
    citiesError.collectWithLifecycle {
      Toast.makeText(context, "Cannot get cities", Toast.LENGTH_SHORT).show()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    searchMenu = menu
    inflater.inflate(R.menu.menu_search, menu.also { it.clear() })
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
      R.id.action_search -> {
        openSearch()
        return true
      }
      R.id.action_clear -> {
        binding.etSearchInput.setText("")
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun openSearch() = with(binding) {
    tvTitle.gone()
    ilSearchInput.gone()
    searchMenu?.findItem(R.id.action_search)?.isVisible = false

    val windowInsetsController = ViewCompat.getWindowInsetsController(etSearchInput)
    windowInsetsController?.show(WindowInsetsCompat.Type.ime())

    etSearchInput.requestFocus()
  }

  override fun onBackPressed() {
    val windowInsets = ViewCompat.getRootWindowInsets(binding.etSearchInput)
    val windowInsetsController = ViewCompat.getWindowInsetsController(binding.etSearchInput)
    when {
      windowInsets?.isVisible(WindowInsetsCompat.Type.ime()) == true ->
        windowInsetsController?.hide(WindowInsetsCompat.Type.ime())
      binding.ilSearchInput.isVisible -> closeSearch()
      else -> super.onBackPressed()
    }
  }

  private fun closeSearch() = with(binding) {
    tvTitle.visible()
    ilSearchInput.gone()
    searchMenu?.findItem(R.id.action_search)?.isVisible = true
  }

  companion object {
    fun newInstance(parent: String) = CityFragment().apply {
      arguments = bundleOf(Constants.ARG_PARENT to parent)
    }
  }
}