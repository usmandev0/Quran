package com.kmpstarter.feature_quran.presentation.ui_main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.theme.Dimens
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ListIem(
    modifier: Modifier = Modifier,
    item: Quran,
    isLastItem:Boolean = false,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCounter(
            modifier = modifier.size(36.dp),
            count = item.id,
        )
        Column(
            modifier = modifier.weight(1f)
                .padding(horizontal = Dimens.paddingSmall),
        ) {
            Text(
                text = item.transliteration,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF240F4F),
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.type.uppercase(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    ),
                )
                Spacer(modifier = modifier.width(Dimens.paddingSmall))
                Box(
                    modifier = modifier.size(Dimens.paddingExtraSmall)
                        .background(color = Color(0xFFBBC4CE), shape = CircleShape)
                )
                Spacer(modifier = modifier.width(Dimens.paddingSmall))
                Text(
                    text = item.total_verses.toString() + " Verses".uppercase(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    ),
                )

            }
        }
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF863ED5),
                fontWeight = FontWeight.Bold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )
    }

    if (!isLastItem){
        Box(
            modifier = modifier.fillMaxWidth().height(1.dp).background(Color(0xFFBBC4CE)),
        )
    }
}

@Preview
@Composable
fun ListIemPreview() {
    ListIem(
        item = Quran(
            id = 1,
            name = "Name",
            total_verses = 10,
            type = "Type",
            translation = "Translation",
            transliteration = "Transliteration",
            verses = emptyList()
        ),
        onItemClick = {}
    )
}

