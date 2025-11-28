package com.kmpstarter.feature_quran.presentation.states

import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.feature_quran.data.data_source.dtos.en.QuranEn

data class QuranState(
    val isLoading: Boolean=false,
    val loadingMessage: String = "",
    val quran: List<Quran> = emptyList(),
    val quranEn: Map<String, List<QuranEn>> = emptyMap(),
    val currentSurah: Quran = Quran.empty()

    )
