package com.kmpstarter.feature_quran.presentation.ui_main.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.core.ui.components.buttons.LoadingButton
import com.kmpstarter.core.ui.composition_locals.LocalTopAppBarScrollBehavior
import com.kmpstarter.core.ui.layouts.lists.CupertinoLazyColumn
import com.kmpstarter.core.ui.modifiers.topAppBarScrollBehavior
import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.feature_quran.presentation.ui_main.cards.SurahCard
import com.kmpstarter.feature_quran.presentation.ui_main.components.ItemAyah
import com.kmpstarter.feature_quran.presentation.viewmodels.QuranViewModel
import com.kmpstarter.theme.Dimens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: QuranViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    var isTranslationShow by remember {
        mutableStateOf(true)
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = state.currentSurah.transliteration,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                navigator.navigateUp()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            isTranslationShow = !isTranslationShow
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = if (isTranslationShow) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        CompositionLocalProvider(LocalTopAppBarScrollBehavior provides scrollBehavior) {
            SurahScreenContent(
                modifier = Modifier
                    .padding(padding)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { focusManager.clearFocus() }
                    ),
                currentSurah = state.currentSurah,
                isTranslationShow = isTranslationShow,
                onPreviousClick = {
                    viewModel.navigateToNextORPreviousSurah(state.currentSurah.id - 1)

                },
                onNextClick = {
                    viewModel.navigateToNextORPreviousSurah(state.currentSurah.id + 1)

                }
            )
        }
    }
}

@Composable
fun SurahScreenContent(
    modifier: Modifier = Modifier,
    currentSurah: Quran,
    isTranslationShow: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

    // Scroll to top whenever surah changes
    LaunchedEffect(currentSurah.id) {
        if (
            currentSurah.verses.size > 50
        ){            coroutine.launch {
                listState.scrollToItem(0)
            }
        }
        else{
            listState.animateScrollToItem(0)
        }
    }

    Column(
        modifier = modifier
            .topAppBarScrollBehavior()
            .fillMaxSize()
            .padding(Dimens.paddingExtraSmall),
    ) {

        CupertinoLazyColumn(
            modifier = Modifier.padding(horizontal = Dimens.paddingSmall),
            state = listState     // << attach listState
        ) {

            item {
                SurahCard(
                    modifier = Modifier
                        .padding(horizontal = Dimens.paddingSmall)
                        .padding(bottom = Dimens.paddingSmall, start = Dimens.paddingSmall),
                    quran = currentSurah
                )
            }

            items(currentSurah.verses) { verse ->
                ItemAyah(
                    verse = verse,
                    isTranslationShow = isTranslationShow,
                    isLast = currentSurah.verses.last() == verse
                )
            }

            item {
                NavigationButtons(
                    isPreviousEnabled = currentSurah.id > 1,
                    isNextEnabled = currentSurah.id < 114,
                    modifier = Modifier
                        .padding(Dimens.paddingSmall)
                        .fillMaxWidth(),
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@Composable
private fun NavigationButtons(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    isPreviousEnabled: Boolean = false,
    onNextClick: () -> Unit,
    isNextEnabled: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
    ) {
        if (isPreviousEnabled) {
            LoadingButton(
                modifier = Modifier.weight(1f),
                text = "Previous",
                onClick = onPreviousClick,
            )
        }
        if (isNextEnabled) {
            LoadingButton(
                modifier = Modifier.weight(1f),
                text = "Next",
                onClick = onNextClick,
            )
        }
    }

}

@Preview
@Composable
fun SurahScreenPreview() {
    SurahScreen()
}
