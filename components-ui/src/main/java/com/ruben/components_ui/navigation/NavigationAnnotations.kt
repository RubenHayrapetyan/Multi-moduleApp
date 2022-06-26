package com.ruben.components_ui.navigation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationHome

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationCountryPickerForHome

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationCountryPickerFromLocation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationLanguagePickerFromTutorial

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationLanguagePickerSettings

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationTutorial

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationTutorialSettings

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationEnableNotification

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NavigationTicketFromDatePicker

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher