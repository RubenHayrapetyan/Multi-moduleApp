package com.ruben.components_data.service

import android.content.Context
import android.content.res.Resources
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@ActivityScoped
class LocaleServiceImpl @Inject constructor(@ActivityContext private val context: Context) :
  LocaleService {
  @Suppress("DEPRECATION")
  override fun changeLocale(lang: String) {
    val appLocale = Locale(lang)
    val res: Resources = context.resources
    val conf = res.configuration
    conf.locale = appLocale
    Locale.setDefault(appLocale)
    val dm = res.displayMetrics
    res.updateConfiguration(conf, dm)
  }
}
