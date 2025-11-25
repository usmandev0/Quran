package com.kmpstarter.core.di

import com.kmpstarter.feature_quran.di.quranModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreModule,
            quranModule
            /*Todo add modules here*/
        )
    }
}