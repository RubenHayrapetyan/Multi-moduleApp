package com.ruben.location.ui.country

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ruben.components_data.eventlistener.EventListener
import com.ruben.components_data.eventlistener.Events
import com.ruben.components_ui.base.BaseFragment
import com.ruben.components_ui.components.attributs.arguments
import com.ruben.components_ui.recyclerview.DividerLineItemDecoration
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.constants.Constants
import com.ruben.extentions.getDrawableFromRes
import com.ruben.extentions.gone
import com.ruben.extentions.visible
import com.ruben.resources.R
import com.ruben.location.databinding.FragmentCountryBinding
import com.ruben.location.navigation.cityPickerScreenForHome
import com.ruben.location.navigation.cityPickerScreenForLocation
import dagger.hilt.android.AndroidEntryPoint
import com.ruben.location.ui.component.CountriesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding, CountryViewModel>() {
  override val binding: FragmentCountryBinding by viewBinding(FragmentCountryBinding::inflate)
  override val viewModel: CountryViewModel by viewModels()

  private val parent: String by arguments(key = Constants.ARG_PARENT, default = Constants.PARENT_HOME)

  @Inject
  lateinit var eventListener: EventListener

  private var searchMenu: Menu? = null

  private val countriesAdapter = CountriesAdapter { country, _ ->
    viewModel.saveCountry(country = country)
    if (parent == Constants.PARENT_HOME) {
      router.navigateTo(screen = cityPickerScreenForHome())
    } else {
      router.navigateTo(screen = cityPickerScreenForLocation())
    }
  }

  override fun initToolbar() {
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
      viewModel.searchCountries(textInput = textInput)
    }
    etSearchInput.post {
      if (!etSearchInput.text.isNullOrEmpty()) {
        openSearch()
      }
    }
    context?.getDrawableFromRes(R.drawable.bg_divider)?.let { divider ->
      val dividerItemDecoration = DividerLineItemDecoration(divider = divider)
      rvCountries.addItemDecoration(dividerItemDecoration)
    }
    rvCountries.adapter = countriesAdapter
  }

  override fun initCollectors() = with(viewModel) {
    countries.collectWithLifecycle { countries ->
      countriesAdapter.submitList(countries)
      binding.lvLoading.gone()
    }
    countriesError.collectWithLifecycle {
      Toast.makeText(context, "Cannot get countries", Toast.LENGTH_SHORT).show()
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
    ilSearchInput.visible()
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
      else -> {
        lifecycleScope.launch { eventListener.sendEvent(key = Events.EVENT_SHOW_NAV) }
        super.onBackPressed()
      }
    }
  }

  private fun closeSearch() = with(binding) {
    tvTitle.isVisible = true
    ilSearchInput.isVisible = false
    searchMenu?.findItem(R.id.action_search)?.isVisible = true
  }

  companion object {
    fun newInstance(parent: String) = CountryFragment().apply {
      arguments = bundleOf(Constants.ARG_PARENT to parent)
    }
  }
}