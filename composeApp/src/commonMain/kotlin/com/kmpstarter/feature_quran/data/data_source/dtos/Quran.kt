package com.kmpstarter.feature_quran.data.data_source.dtos

import kotlinx.serialization.Serializable

@Serializable
data class Quran(
    val id: Int,
    val name: String,
    val total_verses: Int,
    val translation: String,
    val transliteration: String,
    val type: String,
    val verses: List<Verse>
) {
    companion object {
        fun empty() =
            Quran(
                id = 0,
                name = "",
                total_verses = 0,
                translation = "",
                transliteration = "",
                type = "",
                verses = emptyList()
            )

        fun dummy() =
            Quran(
                id = 1,
                name = "Al-Fatiha",
                total_verses = 7,
                translation = "Al-Fatiha",
                transliteration = "Al-Fatiha",
                type = "Al-Fatiha",
                verses = emptyList()
            )

    }
}