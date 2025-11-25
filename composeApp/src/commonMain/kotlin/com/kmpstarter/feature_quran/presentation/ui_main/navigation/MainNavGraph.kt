package com.kmpstarter.feature_quran.presentation.ui_main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.kmpstarter.core.ui.utils.navigation.appNavComposable
import com.kmpstarter.feature_quran.presentation.ui_main.screens.HomeScreen
import com.kmpstarter.feature_quran.presentation.ui_main.screens.NewScreen
import com.kmpstarter.feature_quran.presentation.ui_main.screens.OnBoardScreen
import com.kmpstarter.feature_quran.presentation.ui_main.screens.SplashScreen
import com.kmpstarter.feature_quran.presentation.ui_main.screens.SurahScreen
import com.kmpstarter.starter_features.auth.presentation.ui_main.screens.SignInScreen
import com.kmpstarter.starter_features.auth.presentation.ui_main.screens.SignUpScreen

fun NavGraphBuilder.mainNavGraph(
    scaffoldModifier: Modifier,
) {

    navigation<MainScreens.Root>(
        startDestination = MainScreens.Splash,
    ) {
        appNavComposable<MainScreens.Splash> {
            SplashScreen(
                modifier = scaffoldModifier,
            )
        }
        appNavComposable<MainScreens.OnBoarding> {
            OnBoardScreen(
                modifier = scaffoldModifier,
            )
        }

        appNavComposable<MainScreens.Home> {
            HomeScreen(
                modifier = scaffoldModifier,
            )
        }
        appNavComposable<MainScreens.Surah> {
            SurahScreen(
                modifier = scaffoldModifier,
            )
        }
        appNavComposable<MainScreens.New> {
            NewScreen(
                modifier = scaffoldModifier,
            )
        }




    }
}