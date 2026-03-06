package com.example.composefullnative.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedMeshBackground(isDark: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "bg")
    val shift by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(tween(8000, easing = LinearEasing)),
        label = "shift"
    )

    val primaryColor = if (isDark) Color(0xFF6C63FF) else Color(0xFF4F46E5)
    val accentColor  = if (isDark) Color(0xFFFF6584) else Color(0xFFF43F5E)
    val bgColor      = if (isDark) Color(0xFF0D0D1A) else Color(0xFFF8F7FF)

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(bgColor)

        // Animated blobs
        val blobs = listOf(
            Triple(0.2f, 0.2f, primaryColor.copy(alpha = 0.18f)),
            Triple(0.8f, 0.3f, accentColor.copy(alpha = 0.14f)),
            Triple(0.5f, 0.8f, primaryColor.copy(alpha = 0.12f)),
            Triple(0.1f, 0.7f, accentColor.copy(alpha = 0.10f)),
        )
        blobs.forEachIndexed { i, (cx, cy, color) ->
            val dx = sin(shift + i * 1.5f) * 60f
            val dy = cos(shift + i * 1.2f) * 60f
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(color, Color.Transparent),
                    center = Offset((size.width * cx + dx), (size.height * cy + dy)),
                    radius = size.minDimension * 0.45f
                ),
                radius = size.minDimension * 0.45f,
                center = Offset((size.width * cx + dx), (size.height * cy + dy))
            )
        }

        // Subtle grid lines
        val gridColor = if (isDark) Color.White.copy(alpha = 0.03f) else Color.Black.copy(alpha = 0.03f)
        val step = 48.dp.toPx()
        var x = 0f
        while (x < size.width) { drawLine(gridColor, Offset(x, 0f), Offset(x, size.height), 1f); x += step }
        var y = 0f
        while (y < size.height) { drawLine(gridColor, Offset(0f, y), Offset(size.width, y), 1f); y += step }
    }
}