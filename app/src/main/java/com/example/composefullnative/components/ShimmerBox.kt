package com.example.composefullnative.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerBox(modifier: Modifier = Modifier, isDark: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerX by infiniteTransition.animateFloat(
        initialValue = -1f, targetValue = 2f,
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing)),
        label = "shimmerX"
    )
    val baseColor = if (isDark) Color(0xFF1E1E2E) else Color(0xFFE8E8F0)
    val highlightColor = if (isDark) Color(0xFF2D2D45) else Color(0xFFF5F5FF)

    Box(modifier = modifier
        .clip(RoundedCornerShape(12.dp))
        .drawBehind {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(baseColor, highlightColor, baseColor),
                    start = Offset(shimmerX * size.width, 0f),
                    end = Offset((shimmerX + 0.5f) * size.width, size.height)
                )
            )
        }
    )
}

@Composable
fun FullScreenAdShimmer(isDark: Boolean) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        ShimmerBox(Modifier.fillMaxWidth().height(220.dp), isDark)
        ShimmerBox(Modifier.width(140.dp).height(18.dp), isDark)
        ShimmerBox(Modifier.fillMaxWidth().height(14.dp), isDark)
        ShimmerBox(Modifier.fillMaxWidth(0.8f).height(14.dp), isDark)
        ShimmerBox(Modifier.fillMaxWidth().height(14.dp), isDark)
        Spacer(Modifier.weight(1f))
        ShimmerBox(Modifier.fillMaxWidth().height(52.dp), isDark)
    }
}