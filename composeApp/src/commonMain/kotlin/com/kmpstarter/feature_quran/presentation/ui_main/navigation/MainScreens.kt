package com.kmpstarter.feature_quran.presentation.ui_main.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class MainScreens {
    @Serializable
    data object Root : MainScreens()
    @Serializable
    data object Splash : MainScreens()
    @Serializable
    data object OnBoarding : MainScreens()
    @Serializable
    data object Home : MainScreens()
    @Serializable
    data object Surah : MainScreens()
    @Serializable
    data object New : MainScreens()
    @Serializable
    data object SignUp : MainScreens()

}
