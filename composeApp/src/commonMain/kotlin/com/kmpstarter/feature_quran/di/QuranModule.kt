package com.kmpstarter.feature_quran.di

import com.kmpstarter.feature_quran.presentation.viewmodels.QuranViewModel
import org.koin.dsl.module

val quranModule = module {
    single {
        QuranViewModel(
            navigator = get(),
        )
    }
}