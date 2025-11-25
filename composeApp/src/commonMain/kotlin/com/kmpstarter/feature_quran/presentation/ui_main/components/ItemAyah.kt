package com.kmpstarter.feature_quran.presentation.ui_main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmpstarter.feature_quran.data.data_source.dtos.Verse
import com.kmpstarter.theme.Dimens
import kmpstarter.composeapp.generated.resources.Res
import kmpstarter.composeapp.generated.resources.play_icon
import kmpstarter.composeapp.generated.resources.save_icon
import kmpstarter.composeapp.generated.resources.share_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ItemAyah(
    modifier: Modifier = Modifier,
    verse: Verse,
    isTranslationShow: Boolean = true,
    isLast: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(Dimens.paddingExtraSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = modifier.fillMaxWidth()
                .clip(RoundedCornerShape(15))
                .background(
                    color = Color(0xFFD0CEDE),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(48.dp, 48.dp)
                    .padding(Dimens.paddingSmall)
                    .clip(CircleShape)
                    .background(color = Color(0xFF863ED5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = verse.id.toString(),
                    color = Color.White
                )

            }
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
            ) {
                Image(
                    painter = painterResource(Res.drawable.share_icon),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(Res.drawable.play_icon),
                    contentDescription = null,
                )
                Image(
                    modifier = modifier.padding(end = Dimens.paddingSmall),
                    painter = painterResource(Res.drawable.save_icon),
                    contentDescription = null,
                )
            }

        }
        Spacer(modifier = Modifier.height(Dimens.paddingSmall))
        ArabicText(
            text = verse.text,
        )

        AnimatedVisibility(isTranslationShow)
        {
            Spacer(modifier = Modifier.height(Dimens.paddingSmall))
            UrduText(
                text = verse.translation,
            )
        }

        Spacer(modifier = Modifier.height(Dimens.paddingSmall))


        if (!isLast) {
            Spacer(
                modifier = Modifier.fillMaxWidth(0.92f).height(1.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
            )

        }
    }
}


@Preview
@Composable
fun ItemAyahPreview() {
    ItemAyah(
//        quran = Quran.dummy(),
        verse = Verse(
            id = 1,
            text = "Text",
            translation = "Translation"
        )
    )
}