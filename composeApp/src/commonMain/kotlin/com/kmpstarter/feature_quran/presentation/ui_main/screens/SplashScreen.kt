package com.kmpstarter.feature_quran.presentation.ui_main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.core.ui.composition_locals.LocalTopAppBarScrollBehavior
import com.kmpstarter.core.ui.layouts.lists.ScrollableColumn
import com.kmpstarter.core.ui.modifiers.topAppBarScrollBehavior
import com.kmpstarter.feature_quran.presentation.ui_main.components.SplashBg
import com.kmpstarter.feature_quran.presentation.ui_main.navigation.MainScreens
import com.kmpstarter.feature_quran.presentation.viewmodels.QuranViewModel
import com.kmpstarter.theme.Dimens
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: QuranViewModel = koinInject()
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val route = MainScreens.OnBoarding

    LaunchedEffect(Unit) {
        viewModel.getQuran()
        delay(2000)
        navigator.navigateTo(
            route = route,
            popUpTo = MainScreens.Root,
            inclusive = true
        )
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

