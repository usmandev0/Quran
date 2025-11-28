package com.kmpstarter.feature_quran.presentation.ui_main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kmpstarter.theme.Dimens
import kmpstarter.composeapp.generated.resources.Res
import kmpstarter.composeapp.generated.resources.arabic
import kmpstarter.composeapp.generated.resources.noto
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun ArabicText(
    modifier: Modifier = Modifier,
    text: String= "ﻳِمَلٰعْلا ِّبَر ِهَّلِل ُدْمَحْلا",
    color: Color = Color(0xFF240F4F)
) {
    Text(
        modifier = modifier.fillMaxWidth().padding(Dimens.paddingExtraSmall),
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(
            color = color,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(Res.font.arabic, weight = FontWeight.Normal)
            ),
            lineHeight = 42.sp


        ),
    )
}

@Composable
@Preview
fun UrduText(
    modifier: Modifier = Modifier,
    text: String= "ﻳِمَلٰعْلا ِّبَر ِهَّلِل ُدْمَحْلا",
    color: Color = Color(0xFF240F4F)
) {
    Text(
        modifier = modifier.fillMaxWidth().padding(Dimens.paddingExtraSmall),
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = color,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(Res.font.noto, weight = FontWeight.Normal)
            ),
            lineHeight = 32.sp

        ),
    )
}
@Composable
@Preview
fun EnglishText(
    modifier: Modifier = Modifier,
    text: String= "The quick brown fox jumps over the lazy dog",
    color: Color = Color(0xFF240F4F)
) {
    Text(
        modifier = modifier.fillMaxWidth().padding(Dimens.paddingExtraSmall),
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = color,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp

        ),
    )
}




