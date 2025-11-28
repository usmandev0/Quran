package com.kmpstarter.feature_quran.presentation.ui_main.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.core.ui.composition_locals.LocalTopAppBarScrollBehavior
import com.kmpstarter.core.ui.modifiers.topAppBarScrollBehavior
import com.kmpstarter.feature_quran.presentation.ui_main.navigation.MainScreens
import com.kmpstarter.feature_quran.presentation.viewmodels.QuranViewModel
import com.kmpstarter.theme.Dimens
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: QuranViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    val route = MainScreens.OnBoarding

    LaunchedEffect(Unit) {
        viewModel.getQuran()
    }
    if (state.quran.isNotEmpty()){
        coroutineScope.launch {
            navigator.navigateTo(
                route = route,
                popUpTo = MainScreens.Root,
                inclusive = true
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        CompositionLocalProvider(LocalTopAppBarScrollBehavior provides scrollBehavior) {
            SplashScreenContent(
                modifier = Modifier
                    .padding(padding)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { focusManager.clearFocus() }
                    ),
            )
        }
    }
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .topAppBarScrollBehavior()
            .fillMaxSize()
            .padding(Dimens.paddingSmall),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quran",
            style = MaterialTheme.typography.displayLarge
        )
    }
}



@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

