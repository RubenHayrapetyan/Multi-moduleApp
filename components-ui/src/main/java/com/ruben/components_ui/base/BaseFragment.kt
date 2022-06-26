package com.ruben.components_ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
  abstract val binding: VB
  abstract val viewModel: VM

  @Inject
  protected lateinit var router: Router

  protected val parentActivity: AppCompatActivity?
    get() = (activity as? AppCompatActivity)

  override fun onAttach(context: Context) {
    super.onAttach(context)
    activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        onBackPressed()
      }
    })
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = binding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initToolbar()
    initViews()
    initCollectors()
  }

  protected fun <T> Flow<T>.collectWithLifecycle(collector: suspend (T) -> Unit) {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        this@collectWithLifecycle.collect { data ->
          collector(data)
        }
      }
    }
  }

  protected open fun initToolbar() {}
  protected open fun initViews() {}
  protected open fun initCollectors() {}

  open fun onBackPressed() {
    router.exit()
  }
}
