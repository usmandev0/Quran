package com.kmpstarter.feature_quran.presentation.ui_main.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.theme.Dimens
import kmpstarter.composeapp.generated.resources.Res
import kmpstarter.composeapp.generated.resources.bismila
import kmpstarter.composeapp.generated.resources.quran
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SurahCard(
    modifier: Modifier = Modifier,
    quran: Quran
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(Color(0xFFDF98FA), Color(0xFF9055FF))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.quran),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomEnd)
                .offset(
                    x = 62.dp,
                    y = 72.dp
                ).alpha(0.2f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = quran.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(Dimens.paddingSmall))
            Text(
                text = quran.translation,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(Dimens.paddingLarge))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(1.dp)
                    .background(Color.White.copy(alpha = 0.35f))
            )
            Spacer(modifier = Modifier.height(Dimens.paddingLarge))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quran.type.uppercase(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    ),
                )


                Spacer(modifier = Modifier.width(Dimens.paddingSmall))

                Box(
                    modifier = Modifier.size(Dimens.paddingExtraSmall)
                        .background(color = Color.White.copy(alpha = 0.7f), shape = CircleShape)
                )


                Spacer(modifier = Modifier.width(Dimens.paddingSmall))


                Text(
                    text = "${quran.total_verses} VERSES",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(Res.drawable.bismila),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(Dimens.paddingLarge))
        }
    }
}


@Composable
@Preview
fun SurahCardPreview() {
    SurahCard(
        quran = Quran.dummy()
    )
}