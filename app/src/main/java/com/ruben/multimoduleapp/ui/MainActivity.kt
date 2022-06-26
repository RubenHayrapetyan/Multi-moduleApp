package com.ruben.multimoduleapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.ruben.components_data.service.LocaleService
import com.ruben.components_ui.viewbinding.viewBinding
import com.ruben.multimoduleapp.R
import com.ruben.multimoduleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
  private val mainViewModel: MainViewModel by viewModels()
  private val navigator: Navigator = AppNavigator(this, R.id.navigation_fragment)

  @Inject
  lateinit var navigatorHolder: NavigatorHolder

  @Inject
  lateinit var localeService: LocaleService

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installSplashScreen().also { splashScreen ->
      splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
        ObjectAnimator.ofFloat(splashScreenViewProvider.view, View.ALPHA, 1f, 0f).apply {
          interpolator = AnticipateInterpolator()
          duration = 500L
          doOnEnd { splashScreenViewProvider.remove() }
        }.start()
      }
    }

    setContentView(binding.root)

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        mainViewModel.currentLanguage.collect { locale ->
          if (locale.isNotEmpty()) {
            localeService.changeLocale(locale)
          }
        }
      }
    }
  }

  override fun onResumeFragments() {
    navigatorHolder.setNavigator(navigator = navigator)
    super.onResumeFragments()
  }

  override fun onPause() {
    navigatorHolder.removeNavigator()
    super.onPause()
  }
}