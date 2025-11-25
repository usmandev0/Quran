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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.core.ui.composition_locals.LocalTopAppBarScrollBehavior
import com.kmpstarter.core.ui.modifiers.topAppBarScrollBehavior
import com.kmpstarter.theme.Dimens
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject()
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = "NewScreen",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ) { padding ->
        CompositionLocalProvider(LocalTopAppBarScrollBehavior provides scrollBehavior) {
            NewScreenContent(
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
fun NewScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .topAppBarScrollBehavior()
            .fillMaxSize()
            .padding(Dimens.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.paddingLarge)
    ) {
        Text(text = "NewScreen")
    }
}


@Preview
@Composable
fun NewScreenPreview() {
    NewScreen()
}
