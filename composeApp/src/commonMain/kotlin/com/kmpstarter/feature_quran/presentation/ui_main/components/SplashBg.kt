package com.kmpstarter.feature_quran.presentation.ui_main.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kmpstarter.theme.Dimens
import kmpstarter.composeapp.generated.resources.Res
import kmpstarter.composeapp.generated.resources.cloud_1
import kmpstarter.composeapp.generated.resources.cloud_2
import kmpstarter.composeapp.generated.resources.quran
import kmpstarter.composeapp.generated.resources.quran_bottom_layer
import kmpstarter.composeapp.generated.resources.quran_no_layer
import kmpstarter.composeapp.generated.resources.star
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SplashBg(
    modifier: Modifier = Modifier,
    contentVisibility: Boolean = true,
    starVisibility: Boolean = true
) {

    // Fade & scale for whole content
    val contentAlpha by animateFloatAsState(
        targetValue = if (contentVisibility) 1f else 0f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    val contentScale by animateFloatAsState(
        targetValue = if (contentVisibility) 1f else 0.95f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    // Quran slide-up animation
    val quranOffset by animateFloatAsState(
        targetValue = if (contentVisibility) 0f else 140f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    // Stars gentle floating motion
    val floatOffset by rememberInfiniteTransition().animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            RepeatMode.Reverse
        )
    )

    // Cloud slide-in/transparency
    val cloudAlpha by animateFloatAsState(
        targetValue = if (starVisibility) 1f else 0f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    AnimatedVisibility(contentVisibility) {

        BoxWithConstraints(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = contentAlpha
                    scaleX = contentScale
                    scaleY = contentScale
                }
                .clip(RoundedCornerShape(5))
                .background(Color(0xFF672CBC))
        ) {

            // Stars
            AnimatedVisibility(starVisibility) {
                Stars(floatOffset)
                CloudsLayer(cloudAlpha, maxWidth.value, maxHeight.value)
            }

            // Quran bottom images
            Box(
                modifier = Modifier.fillMaxWidth(0.9f).height(200.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(Res.drawable.quran),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun BoxWithConstraintsScope.Stars(floatOffset: Float) {

    Star(x = 12.dp, y = (maxHeight.value / 5 + floatOffset).dp, size = 24.dp)
    Star(x = (maxWidth.value / 3).dp, y = (maxHeight.value / 4 + floatOffset).dp, size = 16.dp)
    Star(x = (maxWidth.value / 1.5).dp, y = (maxHeight.value / 3.8 + floatOffset).dp, size = 18.dp)
    Star(x = (maxWidth.value / 1.5).dp, y = (maxHeight.value / 9 + floatOffset).dp, size = 12.dp)
    Star(x = (maxWidth.value / 1.3).dp, y = (maxHeight.value / 7 + floatOffset).dp, size = 12.dp)
    Star(x = (maxWidth.value / 3.3).dp, y = (maxHeight.value / 6 + floatOffset).dp, size = 12.dp)
    Star(x = (maxWidth.value.div(2)).dp, y = (maxHeight.value / 2.5 + floatOffset).dp, size = 12.dp)
    Star(x = (maxWidth.value.div(5)).dp, y = (maxHeight.value / 2.4 + floatOffset).dp, size = 16.dp)
    Star(x = (maxWidth.value.div(1.25)).dp, y = (maxHeight.value / 2 + floatOffset).dp, size = 14.dp)
}

@Composable
private fun CloudsLayer(alpha: Float, maxW: Float, maxH: Float) {
    Clouds((-20).dp, (maxH / 4.8).dp, 150.dp, alpha = alpha)
    Clouds((maxW / 1.7).dp, (maxH / 3.8).dp, 150.dp, alpha = alpha)
    Clouds((maxW / 2.5).dp, (maxH / 13).dp, 150.dp, clouds = Res.drawable.cloud_2, alpha = 0.7f)
}

@Composable
private fun Clouds(
    x: Dp, y: Dp, size: Dp,
    clouds: DrawableResource = Res.drawable.cloud_1,
    alpha: Float = 1f
) {
    Image(
        painter = painterResource(clouds),
        contentDescription = null,
        modifier = Modifier
            .offset(x = x, y = y)
            .size(size)
            .graphicsLayer { this.alpha = alpha }
    )
}

@Composable
fun Star(
    x: Dp,
    y: Dp,
    size: Dp
) {
    // Infinite animations
    val infiniteTransition = rememberInfiniteTransition()

    // Alpha (twinkling brightness)
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (800..1600).random(),  // random speed
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Scale effect (small sparkle)
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (900..1400).random(),
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Gentle floating motion
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (1500..2000).random(),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(Res.drawable.star),
        contentDescription = null,
        modifier = Modifier
            .offset(x = x, y = y + floatOffset.dp)
            .size(size)
            .graphicsLayer {
                this.alpha = alpha          // glow change
                this.scaleX = scale         // sparkle
                this.scaleY = scale
            }
    )
}

