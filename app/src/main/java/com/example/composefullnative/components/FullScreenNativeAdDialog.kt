package com.example.composefullnative.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composefullnative.data.NativeAdState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FullScreenNativeAdDialog(
    adState: NativeAdState,
    onDismiss: () -> Unit,
    onCtaClick: (() -> Unit)? = null,
    skipAfterSeconds: Int = 5
) {
    val colorScheme = MaterialTheme.colorScheme
    val isDark = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()

    var countdown by remember { mutableIntStateOf(skipAfterSeconds) }
    var canSkip by remember { mutableStateOf(false) }
    var ctaPressed by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        repeat(skipAfterSeconds) {
            delay(1000)
            countdown--
        }
        canSkip = true
    }

    Dialog(
        onDismissRequest = { if (canSkip) onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = canSkip,
            dismissOnClickOutside = false
        )
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(400)) + slideInVertically(tween(500, easing = FastOutSlowInEasing)) { it / 4 }
        ) {
            Box(Modifier.fillMaxSize()) {

                // Animated Background
                AnimatedMeshBackground(isDark)

                // Glass card overlay
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            if (isDark) Color(0xFF0D0D1A).copy(alpha = 0.6f)
                            else Color(0xFFF8F7FF).copy(alpha = 0.6f)
                        )
                )

                // Content
                if (adState.isLoading) {
                    FullScreenAdShimmer(isDark)
                } else if (adState.error != null) {
                    AdErrorState(isDark, adState.error, onDismiss)
                } else {
                    adState.nativeAd?.let { ad ->
                        FullScreenAdContent(
                            nativeAd = ad,
                            isDark = isDark,
                            colorScheme = colorScheme,
                            ctaPressed = ctaPressed,
                            onCtaClick = {
                                ctaPressed = true
                                scope.launch {
                                    delay(150)
                                    onCtaClick?.invoke()
                                    onDismiss()
                                }
                            }
                        )
                    }
                }

                // Top bar: Ad badge + countdown/skip
                Row(
                    Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AdBadge(isDark)

                    AnimatedContent(targetState = canSkip, label = "skipBtn") { skippable ->
                        if (skippable) {
                            SkipButton(isDark, onDismiss)
                        } else {
                            CountdownCircle(
                                seconds = countdown,
                                total = skipAfterSeconds,
                                isDark = isDark,
                                onDismissEnabled = {}
                            )
                        }
                    }
                }
            }
        }
    }
}