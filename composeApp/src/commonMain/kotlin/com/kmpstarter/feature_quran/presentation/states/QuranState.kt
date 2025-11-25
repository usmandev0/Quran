package com.kmpstarter.feature_quran.presentation.states

import com.kmpstarter.feature_quran.data.data_source.dtos.Quran

data class QuranState(
    val isLoading: Boolean=false,
    val loadingMessage: String = "",
    val quran: List<Quran> = emptyList(),
    val currentSurah: Quran = Quran.empty()

    )
