package com.kmpstarter.feature_quran.presentation.ui_main.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.core.ui.composition_locals.LocalTopAppBarScrollBehavior
import com.kmpstarter.core.ui.layouts.lists.CupertinoLazyColumn
import com.kmpstarter.core.ui.modifiers.topAppBarScrollBehavior
import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.feature_quran.presentation.ui_main.cards.HomeCard
import com.kmpstarter.feature_quran.presentation.ui_main.components.ListIem
import com.kmpstarter.feature_quran.presentation.ui_main.navigation.MainScreens
import com.kmpstarter.feature_quran.presentation.viewmodels.QuranViewModel
import com.kmpstarter.theme.Dimens
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: QuranViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    viewModel.getQuran()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = "Quran",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                           coroutineScope.launch {
                               navigator.navigateTo(
                                   route = MainScreens.New,
                               )
                           }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        CompositionLocalProvider(LocalTopAppBarScrollBehavior provides scrollBehavior) {
            HomeScreenContent(
                modifier = Modifier
                    .padding(padding)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { focusManager.clearFocus() }
                    ),
                quran = state.quran,
                onItemClick = {
                    { viewModel.navigateToSurahScreen(it) }
                }
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    quran: List<Quran>,
    onItemClick: (Quran) -> () -> Unit
) {

    Column(
        modifier = modifier
            .topAppBarScrollBehavior()
            .fillMaxSize()
            .padding(Dimens.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.paddingLarge)
    ) {


        HomeCard()
        CupertinoLazyColumn {
            items(quran) {
                ListIem(
                    item = it,
                    isLastItem = it == quran.last(),
                    onItemClick = onItemClick(it)
                )
            }
        }


    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
