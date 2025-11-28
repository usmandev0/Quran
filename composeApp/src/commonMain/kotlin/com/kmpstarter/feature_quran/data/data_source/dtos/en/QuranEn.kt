package com.kmpstarter.feature_quran.data.data_source.dtos.en

import kotlinx.serialization.Serializable

@Serializable
data class QuranEn(
    val chapter: Int,
    val text: String,
    val verse: Int
){
    companion object{
        fun empty() = QuranEn(
            chapter = 0,
            text = "",
            verse = 0
        )

    }
}