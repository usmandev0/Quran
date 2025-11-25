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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
import com.kmpstarter.theme.Dimens
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject()
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        CompositionLocalProvider(LocalTopAppBarScrollBehavior provides scrollBehavior) {
            OnBoardScreenContent(
                modifier = Modifier
                    .padding(padding)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { focusManager.clearFocus() }
                    ),
                onGetStartClick = {
                    coroutineScope.launch {
                        navigator.navigate(MainScreens.Home)
                    }

                }
            )
        }
    }
}

@Composable
fun OnBoardScreenContent(
    modifier: Modifier = Modifier,
    onGetStartClick: () -> Unit
) {
    Column(
        modifier = modifier
            .topAppBarScrollBehavior()
            .fillMaxSize()
            .padding(Dimens.paddingSmall),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quran App",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color(0xFF672CBC),
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(Dimens.paddingSmall))
            Text(
                text = "Learn Quran and \n Recite once everyday",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        SplashBg(
            modifier = Modifier.height(500.dp)
                .fillMaxWidth(0.9f),
        )


        GetButton(
            modifier = Modifier.fillMaxWidth(0.65f)
                .offset(y = -(Dimens.paddingExtraSmall * 14)),
            text = "Get Started",
            onClick = onGetStartClick
        )

        Spacer(modifier = Modifier.weight(0.5f))


    }
}

@Composable
private fun GetButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.paddingMedium)
            .height(Dimens.buttonHeight + 24.dp)
            .background(color = Color(0xFFF9B091), shape = RoundedCornerShape(50))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

    }

}

@Preview
@Composable
fun OnBoardScreenPreview() {
    OnBoardScreen()
}
