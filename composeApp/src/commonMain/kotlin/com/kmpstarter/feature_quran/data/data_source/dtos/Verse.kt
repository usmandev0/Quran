package com.kmpstarter.feature_quran.data.data_source.dtos

import kotlinx.serialization.Serializable

@Serializable
data class Verse(
    val id: Int,
    val text: String,
    val translation: String
)