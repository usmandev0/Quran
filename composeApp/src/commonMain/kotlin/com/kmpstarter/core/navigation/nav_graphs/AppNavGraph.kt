package com.kmpstarter.core.navigation.nav_graphs

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.kmpstarter.feature_quran.presentation.ui_main.navigation.mainNavGraph

fun NavGraphBuilder.appNavGraph(
    scaffoldModifier: Modifier,
) {
    starterNavGraph(
        scaffoldModifier = scaffoldModifier
    )
    mainNavGraph(
        scaffoldModifier = scaffoldModifier
    )
    /*Todo add other nav graphs here*/
}
